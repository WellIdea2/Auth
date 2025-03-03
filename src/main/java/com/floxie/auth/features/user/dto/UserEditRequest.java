package com.floxie.auth.features.user.dto;

import org.hibernate.validator.constraints.Length;

public record UserEditRequest(

    @Length(min = 4, message = "Username must be at least 4 characters long")
    String username
) {

}