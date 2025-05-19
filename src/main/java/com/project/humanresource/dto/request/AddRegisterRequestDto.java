package com.project.humanresource.dto.request;

import com.project.humanresource.utility.MemberShipType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;

public record AddRegisterRequestDto(
        @NotNull
        @NotEmpty
        @Email
        String email,
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$",
                message = "Password must be at least 8 characters and contain at least one uppercase letter, one lowercase letter, and one number"
        )
        String password,
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$",
                message = "Password must be at least 8 characters and contain at least one uppercase letter, one lowercase letter, and one number"
        )
        String rePassword,

        @NotNull
        @NotEmpty
        String companyName,
        @NotNull
        @NotEmpty
                @Email
        String companyEmail,
        @NotNull
        @NotEmpty
                @Size(min = 11, max = 11)
        String companyPhoneNumber,
        @Enumerated
        MemberShipType memberShipType
) {
}
