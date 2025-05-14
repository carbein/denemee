package com.project.humanresource.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder
public class Employee extends User{

    String firstName;
    String lastName;
    String phoneWork;
    Long companyId;
    String companyName;
    String title;
    Long titleId;
    Long personalFiledId;
    Long userId;
}
