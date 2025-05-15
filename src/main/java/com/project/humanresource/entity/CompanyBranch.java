package com.project.humanresource.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CompanyBranch extends BaseEntity {


    @NotBlank
    String branchName;

    String companyBranchAddress;

    @Pattern(regexp = "^\\d{11}$")
    String companyBranchPhoneNumber;

    String companyBranchEmail;


    @NotNull
     Long companyId;



}
