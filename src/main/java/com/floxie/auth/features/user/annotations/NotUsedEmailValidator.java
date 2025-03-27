package com.floxie.auth.features.user.annotations;

import com.floxie.auth.features.user.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotUsedEmailValidator implements ConstraintValidator<NotUsedEmailConstraint, String> {

  private final UserRepository userRepository;

  @Override
  public boolean isValid(String email, ConstraintValidatorContext context) {
    return userRepository.findByEmail(email).isEmpty();
  }
}
