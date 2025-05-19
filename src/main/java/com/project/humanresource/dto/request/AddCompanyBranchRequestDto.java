package com.project.humanresource.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddCompanyBranchRequestDto(
        @NotBlank String branchName,
        String companyBranchAddress,
        @Pattern(regexp = "^\\d{11}$") String companyBranchPhoneNumber,
        @Email String companyBranchEmail
) {
}
