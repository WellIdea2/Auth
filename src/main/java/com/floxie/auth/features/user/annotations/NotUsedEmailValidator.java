package com.floxie.auth.features.user.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import com.floxie.auth.features.user.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotUsedEmailValidator implements ConstraintValidator<NotUsedEmailConstraint, String> {

  private final UserRepository userRepository;

  @Override
  public boolean isValid(String email, ConstraintValidatorContext context) {
    return userRepository.findByEmail(email)
        .isEmpty();
  }
}
