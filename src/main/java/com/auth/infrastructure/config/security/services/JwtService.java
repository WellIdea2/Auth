package com.auth.infrastructure.config.security.services;

import com.auth.features.user.entity.User;
import com.auth.infrastructure.config.security.dto.AuthenticationResponse;

public interface JwtService {

  AuthenticationResponse generateToken(User user);

  Boolean isAccessTokenValid(String token);

  String getEmailFromToken(String token);
}
