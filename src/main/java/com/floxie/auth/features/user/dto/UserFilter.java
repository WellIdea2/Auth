package com.floxie.auth.features.user.dto;

import org.commons.feature.user.enums.UserRole;

public record UserFilter(String username, String email, UserRole role) {}
