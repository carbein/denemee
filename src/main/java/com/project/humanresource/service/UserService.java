package com.project.humanresource.service;

import com.project.humanresource.config.JwtManager;
import com.project.humanresource.dto.request.AddLoginRequestDto;
import com.project.humanresource.dto.request.AddRegisterRequestDto;
import com.project.humanresource.dto.request.RegisterApproveDto;
import com.project.humanresource.dto.response.LoginResponseDto;
import com.project.humanresource.dto.response.UserResponseDto;
import com.project.humanresource.entity.Company;
import com.project.humanresource.entity.User;
import com.project.humanresource.exception.ErrorType;
import com.project.humanresource.exception.HumanResourceException;
import com.project.humanresource.repository.CompanyRepository;
import com.project.humanresource.repository.UserRepository;
import com.project.humanresource.utility.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final EmailVerificationService emailVerificationService;
    private final JwtManager jwtManager;
    private final UserRoleService userRoleService;
    private final EmployeeService employeeService;


    public void register(AddRegisterRequestDto dto) {
        if (userRepository.existsByEmail(dto.email())) {
            throw new HumanResourceException(ErrorType.EMAIL_ALREADY_EXISTS);
        }

        // Yeni kullanıcı oluşturuluyor.
        User user = User.builder()
                .email(dto.email())
                .password(dto.password())
                .isActive(false)
                .isVerified(false)
                .userRoleId((long) UserStatus.PENDING.ordinal())     // Enum id si olarak atanıyor.
                .build();

        userRepository.save(user);

        Company company = Company.builder()
                .companyName(dto.companyName())
                .companyPhoneNumber(dto.companyPhoneNumber())
                .companyEmail(dto.companyEmail())
                .membershipType(dto.memberShipType())
                .isVerified(false)
                .isActive(false)
                .userId(user.getId())
                .build();

        companyRepository.save(company);
        emailVerificationService.sendVerificationEmail(user.getEmail());
    }

    public String login(AddLoginRequestDto dto) {
        User user = userRepository.findByEmail(dto.email()).orElseThrow(() ->
                new HumanResourceException(ErrorType.USER_NOT_FOUND));
        if (dto.password().equals(user.getPassword())) throw new HumanResourceException(ErrorType.INVALID_PASSWORD);
        if (!user.isActive() || !user.isVerified()) {
            throw new HumanResourceException(ErrorType.USER_NOT_ACTIVE);
        }

        UserStatus role = UserStatus.values()[user.getUserRoleId().intValue()];
        //UserStatus role=userRoleService.resolveUserStatus(user.getUserRoleId());
        String token = jwtManager.createToken(user.getId(), user.getEmail(), role);
        return token;

    }

    public void approveUser(RegisterApproveDto dto) {
        User user = userRepository.findById(dto.userId()).orElseThrow(() -> new HumanResourceException(ErrorType.USER_NOT_FOUND));

        Company company = companyRepository.findByUserId(user.getId()).orElseThrow(() -> new HumanResourceException(ErrorType.COMPANY_NOT_FOUND));
        if (dto.approved()) { // onayladıktan sonra şirket aktif ve pasifliği belli olur.
            user.setVerified(true);
            user.setActive(true);
            user.setUserRoleId((long) UserStatus.COMPANY_ADMIN.ordinal());     // onayladıktan sonra şirket yöneticisi olur

            company.setActive(true);
            company.setVerified(true);

        } else {
            user.setActive(false);
            user.setVerified(false);
            company.setActive(false);
            company.setVerified(false);

        }

    }

    // Onay bekleyen kullanıcı listesi
    public List<UserResponseDto> getPendingUsers() {
        return userRepository.findByIsVerifiedFalse()
                .stream()
                .map(user -> new UserResponseDto(
                        user.getId(),
                        user.getEmail(),
                        user.isActive(),
                        user.isVerified()
                ))
                .collect(java.util.stream.Collectors.toList());
    }

    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }


    public void completeUserRegistration(String email,String password){
        // 1. Yeni kullanıcıyı oluştur
        User user=User.builder()
                .email(email)
                .password(password)
                .userRoleId((long) UserStatus.EMPLOYEE.ordinal())
                .isActive(true)
                .isVerified(true)
                .build();
        userRepository.save(user);

        // 2. Employee eşlemesi yap
        employeeService.assignUserToEmployee(email,user.getId());
    }
}