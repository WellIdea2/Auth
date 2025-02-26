package com.floxie.auth.infrastructure.config.security.services;

import com.floxie.auth.features.user.entity.User;
import com.floxie.auth.infrastructure.config.security.dto.AuthenticationResponse;

public interface JwtService {

  AuthenticationResponse generateToken(User user);

  Boolean isAccessTokenValid(String token);

  String getEmailFromToken(String token);
}
