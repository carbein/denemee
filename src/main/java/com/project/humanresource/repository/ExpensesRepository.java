package com.project.humanresource.repository;

import com.project.humanresource.entity.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpensesRepository extends JpaRepository<Expenses, Long> {

    List<Expenses> findAllByEmployeeId(Long employeeId);        // çalışana ait harcamalar


}
