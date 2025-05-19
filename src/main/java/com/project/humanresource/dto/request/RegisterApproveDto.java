package com.project.humanresource.dto.request;

public record RegisterApproveDto(
        Long userId,
        boolean approved
) {
}
