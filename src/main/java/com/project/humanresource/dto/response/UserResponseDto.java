package com.project.humanresource.dto.response;

public record UserResponseDto (
        Long id,
        String email,
        boolean isActive,
        boolean isVerified
) {
}
