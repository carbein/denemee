package com.project.humanresource.dto.request;

public record AssignDepartmentToBranchRequestDto(
        Long companyBranchId,
        Long departmentId
) {
}
