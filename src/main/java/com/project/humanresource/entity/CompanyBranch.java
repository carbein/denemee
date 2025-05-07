package com.project.humanresource.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CompanyBranch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String companyBranchAddress;
    String companyBranchPhoneNumber;
    String companyBranchEmail;
    Long companyId;

}
