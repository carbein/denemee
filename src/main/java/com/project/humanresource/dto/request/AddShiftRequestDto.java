package com.project.humanresource.dto.request;

import java.time.LocalDateTime;
import java.util.List;

public record AddShiftRequestDto(

        String name,
        LocalDateTime startTime,
        LocalDateTime endTime,
        String description,
        Long employeeName,
        Long shiftBreakId

) {
}
