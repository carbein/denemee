package com.project.humanresource.repository;

import com.project.humanresource.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    List<Department> findAllByCompanyBranchId(Long companyBranchId);    //  Şubeye ait departmanlar

}
