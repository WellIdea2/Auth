package com.floxie.auth.infrastructure.config.security.dto;

import jakarta.validation.constraints.Email;
import com.floxie.auth.features.user.annotations.ValidPassword;

public record AuthenticationRequest(

    @Email(message = "Email must be valid")
    String email,
    @ValidPassword
    String password
) {

}
