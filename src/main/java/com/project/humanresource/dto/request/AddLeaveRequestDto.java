package com.project.humanresource.dto.request;

import java.time.LocalDate;

public record AddLeaveRequestDto(
        LocalDate startDate,
        LocalDate endDate,
        String description,
        String leaveType,
        String state
) {
}
