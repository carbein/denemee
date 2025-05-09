package com.project.humanresource.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user_role")
public class UserRole extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name; // çalışan, şirket yöneticisi, site yöneticisi
} 