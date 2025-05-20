package com.project.humanresource.repository;

import com.project.humanresource.entity.BranchDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchDepartmenRepository extends JpaRepository<BranchDepartment,Long> {


    boolean existsByCompanyBranchIdAndDepartmentId(Long branchId, Long departmentId);

    List<BranchDepartment> findAllByCompanyBranchId(Long branchId);
}
