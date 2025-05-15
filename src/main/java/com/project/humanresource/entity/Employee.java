package com.project.humanresource.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder
@Table(name = "employee")

public class Employee extends BaseEntity {


    @NotBlank
    String firstName;

    @NotBlank
    String lastName;

    @Pattern(regexp = "^\\d{11}$")
    String phoneWork;

    LocalDate hireDate;

    @NotNull
    Long companyId;

    @NotNull
    Long titleId;

    Long personalFiledId;
    @NotNull
    Long userId;


}
