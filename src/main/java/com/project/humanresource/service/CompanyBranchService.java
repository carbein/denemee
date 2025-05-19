package com.project.humanresource.service;

import com.project.humanresource.dto.request.AddCompanyBranchRequestDto;
import com.project.humanresource.entity.Company;
import com.project.humanresource.entity.CompanyBranch;
import com.project.humanresource.entity.User;
import com.project.humanresource.exception.ErrorType;
import com.project.humanresource.exception.HumanResourceException;
import com.project.humanresource.repository.CompanyBranchRepository;
import com.project.humanresource.repository.CompanyRepository;
import com.project.humanresource.repository.UserRepository;
import com.project.humanresource.utility.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyBranchService {
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final CompanyBranchRepository companyBranchRepository;

    public void addBranch(AddCompanyBranchRequestDto dto) {
        String email= SecurityContextHolder.getContext().getAuthentication().getName();

        User user=userRepository.findByEmail(email).orElseThrow(()->new HumanResourceException(ErrorType.USER_NOT_FOUND));

        if (!userRepository.equals(UserStatus.COMPANY_ADMIN.ordinal())){
            throw new HumanResourceException(ErrorType.UNAUTHORIZED);

        }

        Company company= companyRepository.findByUserId(user.getId())
                .orElseThrow(()->new HumanResourceException(ErrorType.COMPANY_NOT_FOUND));

        CompanyBranch companyBranch= CompanyBranch.builder()
                .branchName(dto.branchName())
                .companyBranchAddress(dto.companyBranchAddress())
                .companyBranchPhoneNumber(dto.companyBranchPhoneNumber())
                .companyBranchEmail(dto.companyBranchEmail())
                .companyId(company.getId())
                .build();

        companyBranchRepository.save(companyBranch);

    }
}
