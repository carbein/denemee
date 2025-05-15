package com.project.humanresource.repository;

import com.project.humanresource.entity.ShiftBreakAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShiftBreakAssignmentRepository extends JpaRepository<ShiftBreakAssignment, Long> {

    List<ShiftBreakAssignment> findByShiftId(Long shiftId);
}
