package com.project.humanresource.dto.request;

public record AddUserRequestDto(String email, String password, Long userRoleId) {}

// Gerekirse başka alanlar eklenebilir (ör: name, surname) 