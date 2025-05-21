package com.project.humanresource.service;

import com.project.humanresource.dto.request.AddDepartmentRequesDto;
import com.project.humanresource.entity.Company;
import com.project.humanresource.entity.Department;
import com.project.humanresource.entity.User;
import com.project.humanresource.entity.UserRole;
import com.project.humanresource.exception.ErrorType;
import com.project.humanresource.exception.HumanResourceException;
import com.project.humanresource.repository.CompanyRepository;
import com.project.humanresource.repository.DepartmentRepository;
import com.project.humanresource.repository.UserRepository;
import com.project.humanresource.utility.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final UserRoleService userRoleService;

    public void addDepartment(AddDepartmentRequesDto dto) {
        String email= SecurityContextHolder.getContext().getAuthentication().getName();

        User user=userRepository.findByEmail(email)
                .orElseThrow(()-> new HumanResourceException(ErrorType.USER_NOT_FOUND));

        UserRole userRole=userRoleService.findByUserId(user.getId());

        if (!userRole.getUserStatus().equals(UserStatus.COMPANY_ADMIN)){
            throw new HumanResourceException(ErrorType.UNAUTHORIZED);
        }

        Company company=companyRepository.findByUserId(user.getId())
                .orElseThrow(()-> new HumanResourceException(ErrorType.COMPANY_NOT_FOUND));

        Department department= Department.builder()
                .departmentName(dto.departmentName())
                .companyId(company.getId())
                .build();

        departmentRepository.save(department);


    }
}
