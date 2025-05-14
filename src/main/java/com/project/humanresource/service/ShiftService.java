package com.project.humanresource.service;

import com.project.humanresource.dto.request.AddShiftRequestDto;
import com.project.humanresource.entity.Employee;
import com.project.humanresource.entity.Shift;
import com.project.humanresource.exception.ErrorType;
import com.project.humanresource.exception.HumanResourceException;
import com.project.humanresource.repository.EmployeeRepository;
import com.project.humanresource.repository.ShiftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShiftService {

    public final ShiftRepository shiftRepository;
    private final EmployeeRepository employeeRepository;

    public Shift addShift(AddShiftRequestDto dto) {
        Employee employee = employeeRepository
                .findByFirstNameAndLastName(dto.employeeFirstName(), dto.employeeLastName())
                .orElseThrow(() -> new HumanResourceException(ErrorType.EMPLOYEE_NOT_FOUND));

        Shift shift = Shift.builder()
                .name(dto.name())
                .startTime(dto.startTime())
                .endTime(dto.endTime())
                .description(dto.description())
                .employeeIds(List.of(employee.getId()))
                .shiftBreakIds(List.of(dto.shiftBreakId()))
                .build();
        shiftRepository.save(shift);
        return shift;
    }
}
