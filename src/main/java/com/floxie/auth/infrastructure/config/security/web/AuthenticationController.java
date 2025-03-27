package com.floxie.auth.infrastructure.config.security.web;

import static com.floxie.auth.infrastructure.exceptions.ExceptionMessages.INVALID_USERNAME_OR_PASSWORD;

import com.floxie.auth.features.user.services.UserService;
import com.floxie.auth.infrastructure.config.security.dto.AuthenticationRequest;
import com.floxie.auth.infrastructure.config.security.dto.AuthenticationResponse;
import com.floxie.auth.infrastructure.config.security.services.AccessTokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.commons.exceptions.throwable.BadRequestException;
import org.commons.feature.user.paths.AuthenticationControllerPaths;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AuthenticationControllerPaths.BASE)
@RequiredArgsConstructor
public class AuthenticationController {

  private final UserService userService;
  private final PasswordEncoder passwordEncoder;
  private final AccessTokenService accessTokenService;

  @PostMapping(AuthenticationControllerPaths.LOGIN)
  public ResponseEntity<AuthenticationResponse> create(
      @Valid @RequestBody AuthenticationRequest authenticationRequest) {
    var user = userService.findByEmail(authenticationRequest.email());

    if (passwordEncoder.matches(authenticationRequest.password(), user.getPassword())) {
      return ResponseEntity.ok().body(accessTokenService.generateToken(user));
    }
    throw new BadRequestException(INVALID_USERNAME_OR_PASSWORD);
  }
}
