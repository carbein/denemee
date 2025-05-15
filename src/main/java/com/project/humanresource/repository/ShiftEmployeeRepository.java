package com.project.humanresource.repository;

import com.project.humanresource.entity.ShiftEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShiftEmployeeRepository extends JpaRepository<ShiftEmployee, Long> {
    List<ShiftEmployee> findByEmployeeId(long employeeId);
}
