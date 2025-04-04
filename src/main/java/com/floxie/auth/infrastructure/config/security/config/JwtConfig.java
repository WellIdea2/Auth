package com.floxie.auth.infrastructure.config.security.config;

import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import javax.crypto.SecretKey;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class JwtConfig {

  private SecretKey secretKey;

  @Value("${jwt.expiration-time}")
  private long jwtDuration;

  @Value("${jwt-secret-keyWord}")
  private String secret;

  @PostConstruct
  public void init() {
    this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
  }
}
