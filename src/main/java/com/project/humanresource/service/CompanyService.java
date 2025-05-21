package com.project.humanresource.service;

import com.project.humanresource.dto.request.UpdateCompanyRequestDto;
import com.project.humanresource.entity.Company;
import com.project.humanresource.entity.User;
import com.project.humanresource.entity.UserRole;
import com.project.humanresource.exception.ErrorType;
import com.project.humanresource.exception.HumanResourceException;
import com.project.humanresource.repository.CompanyRepository;
import com.project.humanresource.repository.UserRepository;
import com.project.humanresource.utility.UserStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final UserRoleService userRoleService;

    public void updateCompany(UpdateCompanyRequestDto dto) {
        String email= SecurityContextHolder.getContext().getAuthentication().getName();

        User user=userRepository.findByEmail(email).orElseThrow(()->new HumanResourceException(ErrorType.USER_NOT_FOUND));

        UserRole userRole=userRoleService.findByUserId(user.getId());

        if (!userRole.getUserStatus().equals(UserStatus.COMPANY_ADMIN)){
            throw new HumanResourceException(ErrorType.UNAUTHORIZED);
        }
        Company company=companyRepository.findByUserId(user.getId())
                .orElseThrow(()->new HumanResourceException(ErrorType.USER_NOT_FOUND));
        company.setCompanyName(dto.companyName());
        company.setCompanyEmail(dto.companyAddress());
        company.setCompanyEmail(dto.companyEmail());
        company.setCompanyPhoneNumber(dto.companyPhoneNumber());


        companyRepository.save(company);
    }
}
