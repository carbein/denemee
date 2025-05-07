package com.project.humanresource.repository;

import com.project.humanresource.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository <Company , Long> {
}
