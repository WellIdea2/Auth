package com.floxie.auth.infrastructure.config;

import lombok.RequiredArgsConstructor;
import org.commons.exceptions.CustomExceptionHandler;
import org.commons.exceptions.GlobalExceptionHandler;
import org.commons.feature.shared.utils.GsonWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class Application {

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
}

