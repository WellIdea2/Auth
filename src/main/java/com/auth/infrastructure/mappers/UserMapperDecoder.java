package com.auth.infrastructure.mappers;

import com.auth.features.user.dto.UserCreateRequest;
import com.auth.features.user.entity.User;
import com.auth.features.user.dto.UserEditRequest;
import org.commons.feature.user.dto.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public abstract class UserMapperDecoder implements UserMapper {

  private UserMapper delegate;
  private PasswordEncoder passwordEncoder;

  @Override
  public User toEntity(UserCreateRequest dto) {
    var user = delegate.toEntity(dto);
    user.setPassword(passwordEncoder.encode(dto.password()));
    return user;
  }

  @Override
  public UserView toView(User entity) {
    return delegate.toView(entity);
  }

  @Override
  public void update(User entity, UserEditRequest dto) {
    delegate.update(entity, dto);
  }

  @Autowired
  public void setDelegate(@Qualifier("delegate") UserMapper delegate) {
    this.delegate = delegate;
  }

  @Autowired
  public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }
}
