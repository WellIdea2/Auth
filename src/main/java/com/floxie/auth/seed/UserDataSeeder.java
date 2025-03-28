package com.floxie.auth.seed;

import com.floxie.auth.features.user.entity.User;
import com.floxie.auth.features.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.commons.feature.user.enums.UserRole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
@Order
public class UserDataSeeder implements CommandLineRunner {

  public static final String MAIN_ADMIN_EMAIL = "admin@gmail.com";
  public static final String RANDOM_USER_EMAIL = "user@gmail.com";
  public static final String RANDOM_USER_EMAIL2 = "user2@gmail.com";
  public static final String RANDOM_USER_EMAIL3 = "user3@gmail.com";
  public static final String RANDOM_USER_EMAIL4 = "user4@gmail.com";
  public static final String RANDOM_USER_EMAIL5 = "user5@gmail.com";

  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;

  @Override
  public void run(String... args) {
    log.info("Seeding admin user data");

    seedUser(MAIN_ADMIN_EMAIL, "admin", UserRole.ADMIN);

    log.info("Admin user seeded successfully");
    log.info("Seeding random user data");

    seedUser(RANDOM_USER_EMAIL, "user1", UserRole.USER);
    seedUser(RANDOM_USER_EMAIL2, "user2", UserRole.USER);
    seedUser(RANDOM_USER_EMAIL3, "user3", UserRole.USER);
    seedUser(RANDOM_USER_EMAIL4, "user4", UserRole.USER);
    seedUser(RANDOM_USER_EMAIL5, "user5", UserRole.USER);

    log.info("Random users seeded successfully");
  }

  private void seedUser(String email, String username, UserRole role) {
    if (!userRepository.existsByEmail(email)) {
      var user = new User();
      user.setEmail(email);
      user.setPassword(passwordEncoder.encode("Password123!"));
      user.setUsername(username);
      user.setRole(role);
      userRepository.save(user);
    }
  }
}
