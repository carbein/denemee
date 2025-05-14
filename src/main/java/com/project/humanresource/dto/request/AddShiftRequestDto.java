package com.project.humanresource.dto.request;

import java.time.LocalDateTime;

public record AddShiftRequestDto(

        String name,
        LocalDateTime startTime,
        LocalDateTime endTime,
        String description,
        String employeeFirstName,
        String employeeLastName,
        Long shiftBreakId

) {
}
