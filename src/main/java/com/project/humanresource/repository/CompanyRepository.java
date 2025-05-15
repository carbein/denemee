package com.project.humanresource.repository;

import com.project.humanresource.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository <Company , Long> {

    boolean existsByCompanyEmail(String companyEmail);      //  e mail benzersiz mi kontrol

    Company findByCompanyEmail(String companyEmail);    // giriş ve onay için lazım

    List<Company> findAllByIsActiveTrue();      //  aktif şirktleri listele


}
