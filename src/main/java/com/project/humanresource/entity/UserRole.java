package com.project.humanresource.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRole {
    private Long id;
    private String name; // employee, company manager, site admin
} 