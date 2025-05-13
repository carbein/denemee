package com.project.humanresource.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequestDto {
    private String email;
    private String password;
    private Long userRoleId;
    // Gerekirse başka alanlar eklenebilir (ör: name, surname)
} 