package com.project.humanresource.dto.request;

import java.time.LocalDate;

public record AddAssignmentRequestDto(
        String description,
        String category,
        String serialNumber,
        LocalDate assignmentDate,
        LocalDate returnDate
) {
}
