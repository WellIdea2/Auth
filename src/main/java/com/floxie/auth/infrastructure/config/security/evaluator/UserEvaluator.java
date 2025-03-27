package com.floxie.auth.infrastructure.config.security.evaluator;

import com.floxie.auth.infrastructure.config.security.utils.SecurityUtils;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class UserEvaluator {

  public boolean isOwner(UUID userId) {
    var user = SecurityUtils.extractLoggedUser();
    return user.getId().equals(userId);
  }
}
