package com.project.humanresource.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Long id;
    private String email;
    private String password;
    private boolean isActive = false;
    private UserRole userRole;
}
