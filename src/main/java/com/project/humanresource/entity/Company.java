package com.project.humanresource.entity;

import com.project.humanresource.utility.MemberShipType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Company extends BaseEntity {

    @NotBlank
    String companyName;

    String companyAddress;

    @NotBlank
    @Pattern(regexp = "^\\d{11}$")
    String companyPhoneNumber;
    @Email
    @NotBlank
    @Column(unique = true)
    String companyEmail;

    boolean isVerified = false;

    @Enumerated(EnumType.STRING)
    MemberShipType membershipType; // örn: "Aylık", "Yıllık"

    LocalDateTime membershipExpiryDate;

    Long userId;


}


