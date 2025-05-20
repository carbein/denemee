package com.project.humanresource.dto.request;

public record AssignTitleToEmployeeRequestDto(
        Long employeeId,
        Long titleId
) {
}
