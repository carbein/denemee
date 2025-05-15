package com.project.humanresource.repository;

import com.project.humanresource.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRepository extends JpaRepository<Leave, Long> {

    List<Leave> findAllByEmployeeId(Long employeeId);   //çalışanın izinleri

}
