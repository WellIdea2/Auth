package com.floxie.auth.infrastructure.config.security.utils;

import com.floxie.auth.infrastructure.config.security.dto.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtils {

  private SecurityUtils() {}

  public static CustomUserDetails extractLoggedUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
      return (CustomUserDetails) authentication.getPrincipal();
    } else {
      throw new IllegalStateException(
          "No user is currently authenticated or the principal is not of type CustomUserDetails.");
    }
  }
}
