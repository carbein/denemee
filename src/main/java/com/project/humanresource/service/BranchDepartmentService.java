package com.project.humanresource.service;

import com.project.humanresource.dto.request.AssignDepartmentToBranchRequestDto;
import com.project.humanresource.dto.response.BranchDepartmentResponseDto;
import com.project.humanresource.entity.*;
import com.project.humanresource.exception.ErrorType;
import com.project.humanresource.exception.HumanResourceException;
import com.project.humanresource.repository.*;
import com.project.humanresource.utility.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchDepartmentService {

    public final BranchDepartmenRepository branchDepartmenRepository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final CompanyBranchRepository companyBranchRepository;
    private final DepartmentRepository departmentRepository;
    private final UserRoleService userRoleService;

    public void assignDepartmentBranch(AssignDepartmentToBranchRequestDto dto) {
        String email= SecurityContextHolder.getContext().getAuthentication().getName();

        User user=userRepository.findByEmail(email)
                .orElseThrow(()->new HumanResourceException(ErrorType.USER_NOT_FOUND));

        UserRole userRole=userRoleService.findByUserId(user.getId());

        if (!userRole.getUserStatus().equals(UserStatus.COMPANY_ADMIN)){
            throw new HumanResourceException(ErrorType.UNAUTHORIZED);
        }

        Company company=companyRepository.findByUserId(user.getId())
                .orElseThrow(()->new HumanResourceException(ErrorType.COMPANY_NOT_FOUND));

        CompanyBranch companyBranch=companyBranchRepository.findById(dto.companyBranchId())
                .orElseThrow(()->new HumanResourceException(ErrorType.COMPANY_BRANCH_NOT_FOUND));

        Department department=departmentRepository.findById(dto.departmentId())
                .orElseThrow(()->new HumanResourceException(ErrorType.DEPARTMENT_NOT_FOUND));

        if (!department.getCompanyId().equals(company.getId())|| !companyBranch.getCompanyId().equals(company.getId())){
            throw new HumanResourceException(ErrorType.UNAUTHORIZED);
        }

        if (branchDepartmenRepository.existsByCompanyBranchIdAndDepartmentId(companyBranch.getId(),department.getId())){
            throw new HumanResourceException(ErrorType.ALREADY_EXISTS);
        }

        BranchDepartment branchDepartment= BranchDepartment.builder()
                .companyBranchId(companyBranch.getId())
                .departmentId(department.getId())
                .build();

        branchDepartmenRepository.save(branchDepartment);
    }

    public List<BranchDepartmentResponseDto> getDepartmentBranchId(Long branchId) {
        String email=SecurityContextHolder.getContext().getAuthentication().getName();

        User user=userRepository.findByEmail(email)
                .orElseThrow(()->new HumanResourceException(ErrorType.USER_NOT_FOUND));

        UserRole userRole=userRoleService.findByUserId(user.getId());

        if (!userRole.getUserStatus().equals(UserStatus.COMPANY_ADMIN)){
            throw new HumanResourceException(ErrorType.UNAUTHORIZED);
        }

        Company company=companyRepository.findByUserId(user.getId())
                .orElseThrow(()->new HumanResourceException(ErrorType.COMPANY_NOT_FOUND));

        CompanyBranch companyBranch=companyBranchRepository.findById(company.getId())
                .orElseThrow(()->new HumanResourceException(ErrorType.COMPANY_BRANCH_NOT_FOUND));

        if (!companyBranch.getCompanyId().equals(company.getId())){
            throw new HumanResourceException(ErrorType.UNAUTHORIZED);
        }

        List<BranchDepartment> mappings=branchDepartmenRepository.findAllByCompanyBranchId(branchId);

        return mappings.stream()
                .map(mapping->{
                    Department deparment=departmentRepository.findById(mapping.getDepartmentId())
                            .orElseThrow(()->new HumanResourceException(ErrorType.DEPARTMENT_NOT_FOUND));
                    return new BranchDepartmentResponseDto(deparment.getId(),deparment.getDepartmentName());
                }).toList();
    }
}
