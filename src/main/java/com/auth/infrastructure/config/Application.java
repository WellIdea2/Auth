package com.auth.infrastructure.config;

import lombok.RequiredArgsConstructor;
import org.commons.exceptions.CustomExceptionHandler;
import org.commons.exceptions.GlobalExceptionHandler;
import org.commons.feature.shared.util.GsonWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class Application {

  private final UserDetailsService userDetailsService;

  @Bean
  GsonWrapper gsonWrapper() {
    return new GsonWrapper();
  }

  @Bean
  public CustomExceptionHandler customExceptionHandler() {
    return new CustomExceptionHandler();
  }

  @Bean
  public GlobalExceptionHandler globalExceptionHandler() {
    return new GlobalExceptionHandler();
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    var authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}

