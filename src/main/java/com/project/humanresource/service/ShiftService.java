package com.project.humanresource.service;

import com.project.humanresource.dto.request.AddShiftRequestDto;
import com.project.humanresource.entity.Shift;
import com.project.humanresource.repository.ShiftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShiftService {

    public final ShiftRepository shiftRepository;

    public Shift addShift(AddShiftRequestDto dto) {
        Shift shift = Shift.builder()
                .name(dto.name())
                .startTime(dto.startTime())
                .endTime(dto.endTime())
                .description(dto.description())
                .shiftBreakIds(dto.shiftBreakIds())
                .build();
        shiftRepository.save(shift);
        return shift;
    }
}
