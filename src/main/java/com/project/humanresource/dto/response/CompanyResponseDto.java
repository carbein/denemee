package com.project.humanresource.dto.response;

import java.time.LocalDateTime;

public record CompanyResponseDto(
        Long id,
        String companyName,
        String companyAddress,
        String companyPhoneNumber,
        String companyEmail,
        String membershipType,
        boolean isVerified,
        LocalDateTime membershipExpiryDate,
        boolean isActive,
        LocalDateTime createdAt
) {
}
