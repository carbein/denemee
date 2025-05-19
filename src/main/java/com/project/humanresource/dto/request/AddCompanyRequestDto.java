package com.project.humanresource.dto.request;

public record AddCompanyRequestDto(
        String companyName,
        String companyAddress,
        String companyPhoneNumber,
        String companyEmail,
        Long employerId,
        String membershipType
) {
}
