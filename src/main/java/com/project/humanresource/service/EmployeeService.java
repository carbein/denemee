package com.project.humanresource.service;

import com.project.humanresource.dto.request.AddEmployeeRequestDto;
import com.project.humanresource.dto.request.AssignTitleToEmployeeRequestDto;
import com.project.humanresource.dto.request.SetPersonelFileRequestDto;
import com.project.humanresource.entity.Company;
import com.project.humanresource.entity.Employee;
import com.project.humanresource.entity.PersonalFile;
import com.project.humanresource.entity.User;
import com.project.humanresource.exception.ErrorType;
import com.project.humanresource.exception.HumanResourceException;
import com.project.humanresource.repository.*;
import com.project.humanresource.utility.UserStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    // Geçici: email → employeeId eşlemesi için in-memory map
    private static final ConcurrentHashMap<String, Long> emailToEmployeeMap = new ConcurrentHashMap<>();
    private final UserRepository userRepository;
    private final PersonelFileRepository personelFileRepository;
    private final CompanyRepository companyRepository;
    private final CompanyTitleRepository companyTitleRepository;


    public void addEmployee( AddEmployeeRequestDto dto, Long companyId) {

            Employee employee= Employee.builder()
                    .firstName(dto.name())
                    .lastName(dto.surname())
                    .phoneWork(dto.phoneNumber())
                    .companyId(companyId)
                    .titleId(dto.titleId())
                    .userId(null)
                    .personalFiledId(null)


                    .build();

            employeeRepository.save(employee);

        // Eşleşme için email üzerinden kaydı sakla
            emailToEmployeeMap.put(dto.email(),employee.getId());

    }

    // Şifre oluşturulduktan sonra eşleştirme işlemi yapılır
    public void assignUserToEmployee(String email,Long userId){
        Long employeeId=emailToEmployeeMap.get(email);
        if (employeeId !=null){
                Employee employee=employeeRepository.findById(employeeId)
                        .orElseThrow(()->new RuntimeException("Employe bulunamadı"));
                employee.setUserId(userId);
                employeeRepository.save(employee);
                emailToEmployeeMap.remove(email); // eşleştirme tamamlandıysa map’ten sil
        }

    }

    public void setPersonelFile( SetPersonelFileRequestDto dto) {
        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userRepository.findByEmail(email)
                .orElseThrow(()->new HumanResourceException(ErrorType.USER_NOT_FOUND));

        if (!user.getUserRoleId().equals(UserStatus.EMPLOYEE.ordinal())){
            throw new HumanResourceException(ErrorType.UNAUTHORIZED);
        }

        Employee employee=employeeRepository.findByUserId(user.getId())
                .orElseThrow(()->new HumanResourceException(ErrorType.USER_NOT_FOUND));

        if (personelFileRepository.existsById(employee.getId())){
            throw new HumanResourceException(ErrorType.DUPLICATE_PERSONAL_FILE);

        }
        PersonalFile personalFile= PersonalFile.builder()
                .gender(dto.gender())
                .birthdate(dto.birthdate())
                .personalPhone(dto.personalPhone())
                .personalEmail(dto.personalEmail())
                .nationalId(dto.nationalId())
                .educationLevel(dto.educationLevel())
                .maritalStatus(dto.maritalStatus())
                .bloodType(dto.bloodType())
                .numberOfChildren(dto.numberOfChildren())
                .address(dto.address())
                .city(dto.city())
                .iban(dto.iban())
                .bankName(dto.bankName())
                .bankAccountNumber(dto.bankAccountNumber())
                .bankAccountType(dto.bankAccountType())
                .employeeId(employee.getId())
                .build();

        personelFileRepository.save(personalFile);

    }

    public void assignTitleToEmployee(AssignTitleToEmployeeRequestDto dto) {
        String email= SecurityContextHolder.getContext().getAuthentication().getName();

        User user=userRepository.findByEmail(email)
                .orElseThrow(()->new HumanResourceException(ErrorType.USER_NOT_FOUND));

        if (!user.getUserRoleId().equals(UserStatus.COMPANY_ADMIN.ordinal())){
            throw new HumanResourceException(ErrorType.UNAUTHORIZED);
        }

        Company company=companyRepository.findByUserId(user.getId())
                .orElseThrow(()->new HumanResourceException(ErrorType.COMPANY_NOT_FOUND));

        Employee employee=employeeRepository.findById(dto.employeeId())
                .orElseThrow(()->new HumanResourceException(ErrorType.EMPLOYEE_NOT_FOUND));

        if (!employee.getCompanyId().equals(company.getId())){
            throw new HumanResourceException(ErrorType.UNAUTHORIZED);
        }

        if (!companyTitleRepository.existsByCompanyIdAndTitleId(company.getId(),dto.titleId())){
            throw new HumanResourceException(ErrorType.UNAUTHORIZED);
        }

        employee.setTitleId(dto.titleId());
        employeeRepository.save(employee);
    }

    public void setEmployeeActiveStatus(Long employeeId,boolean isActive) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new HumanResourceException(ErrorType.USER_NOT_FOUND));

        if (!user.getUserRoleId().equals(UserStatus.COMPANY_ADMIN.ordinal())){
            throw new HumanResourceException(ErrorType.UNAUTHORIZED);
        }


        Company company = companyRepository.findByUserId(user.getId())
                .orElseThrow(() -> new HumanResourceException(ErrorType.COMPANY_NOT_FOUND));

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new HumanResourceException(ErrorType.EMPLOYEE_NOT_FOUND));

        if (!employee.getCompanyId().equals(company.getId()))
            throw new HumanResourceException(ErrorType.UNAUTHORIZED);

        employee.setActive(isActive);
        employeeRepository.save(employee);
    }

    public void deleteEmployee(Long employeeId) {
        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userRepository.findByEmail(email)
                .orElseThrow(()->new HumanResourceException(ErrorType.USER_NOT_FOUND));
        if (!user.getUserRoleId().equals(UserStatus.COMPANY_ADMIN.ordinal())){
            throw new HumanResourceException(ErrorType.UNAUTHORIZED);
        }
        Company company=companyRepository.findByUserId(user.getId())
                .orElseThrow(()->new HumanResourceException(ErrorType.COMPANY_NOT_FOUND));
        Employee employee=employeeRepository.findById(employeeId)
                .orElseThrow(()->new HumanResourceException(ErrorType.EMPLOYEE_NOT_FOUND));

        if (!employee.getCompanyId().equals(company.getId())){
            throw new HumanResourceException(ErrorType.UNAUTHORIZED);
        }
// 4. PersonalFile varsa sil
        personelFileRepository.findByEmployeeId(employee.getId())
                        .ifPresent(personelFileRepository::delete);

        // 5. Bağlı user varsa sil (şifreli login olacaksa dikkatli ol)
        if (employee.getUserId() !=null){
            userRepository.findById(employee.getUserId())
                    .ifPresent(userRepository::delete);
        }
        employeeRepository.deleteById(employeeId);
    }


}
