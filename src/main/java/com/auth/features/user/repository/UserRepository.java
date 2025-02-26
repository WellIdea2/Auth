package com.auth.features.user.repository;

import java.util.Optional;
import java.util.UUID;
import com.auth.features.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User> {

  Optional<User> findByEmail(String email);
}
