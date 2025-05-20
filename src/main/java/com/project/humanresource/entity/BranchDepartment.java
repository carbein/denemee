package com.project.humanresource.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tblbranchdepartment")
public class BranchDepartment extends BaseEntity {

        Long companyBranchId;
        Long departmentId;

}
