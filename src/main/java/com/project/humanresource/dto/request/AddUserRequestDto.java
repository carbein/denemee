package com.project.humanresource.dto.request;

public record AddUserRequestDto(
        String email,
        String password){}


// Gerekirse başka alanlar eklenebilir (ör: name, surname) 