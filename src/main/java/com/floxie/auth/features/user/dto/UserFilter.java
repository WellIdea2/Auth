package com.floxie.auth.features.user.dto;

import org.commons.feature.user.enums.Gender;
import org.commons.feature.user.enums.UserRole;
import org.commons.feature.user.enums.WorkoutState;

public record UserFilter(
    String username,
    String email,
    Double kilograms,
    Double height,
    Integer age,
    WorkoutState workoutState,
    Gender gender,
    UserRole role
) {}