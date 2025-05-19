package com.project.humanresource.dto.request;

import com.project.humanresource.utility.MemberShipType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UpdateCompanyRequestDto(
        @NotBlank String companyName,
        String companyAddress,
        @NotBlank @Pattern(regexp = "^\\d{11}$") String companyPhoneNumber,
        @NotBlank @Email String companyEmail

) {
}
