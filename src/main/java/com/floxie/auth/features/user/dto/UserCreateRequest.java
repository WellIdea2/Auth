package com.floxie.auth.features.user.dto;

import com.floxie.auth.features.user.annotations.NotUsedEmailConstraint;
import com.floxie.auth.features.user.annotations.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCreateRequest(
    @Size(min = 4, message = "Username must be at least 4 characters long")
        @NotBlank(message = "Username cannot be blank")
        String username,
    @NotUsedEmailConstraint(message = "Email is already in use")
        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Email should be valid")
        String email,
    @NotBlank(message = "Password cannot be blank")
        @ValidPassword(message = "Password must meet the complexity requirements")
        String password) {}
