package com.project.humanresource.dto.request;

public record AddExpenseRequestDto(
        String description,
        String documentUrl,
        String state,
        String category,
        String amount
) {
}
