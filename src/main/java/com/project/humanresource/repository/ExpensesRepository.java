package com.project.humanresource.repository;

import com.project.humanresource.entity.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpensesRepository extends JpaRepository<Expenses, Long> {

}
