package com.project.humanresource.dto.request;

public record AddLoginRequestDto(
        String email,
        String password
) {
}
