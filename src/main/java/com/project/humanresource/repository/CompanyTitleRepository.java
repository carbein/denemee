package com.project.humanresource.repository;

import com.project.humanresource.entity.CompanyTitle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyTitleRepository extends JpaRepository<CompanyTitle, Long> {

    List<CompanyTitle> findByCompanyId(Long companyId); // şirketin kullanabileceği unvanlar

    boolean existsByCompanyIdAndTitleId(Long id, Long aLong);
}
