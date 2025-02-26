package com.floxie.auth.features.user.services;

import static com.floxie.auth.infrastructure.exceptions.ExceptionMessages.USER_NOT_FOUND;
import static com.floxie.auth.infrastructure.exceptions.ExceptionMessages.USER_NOT_FOUND_EMAIL;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import com.floxie.auth.features.user.dto.UserFilter;
import com.floxie.auth.features.user.entity.User;
import com.floxie.auth.features.user.repository.UserRepository;
import com.floxie.auth.features.user.repository.UserSpecification;
import com.floxie.auth.infrastructure.config.security.services.UserDetailsServiceImpl;
import com.floxie.auth.infrastructure.mappers.UserMapper;
import com.floxie.auth.features.user.dto.UserEditRequest;
import org.commons.feature.user.dto.UserView;
import org.commons.exceptions.throwable.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

  private final UserRepository repository;
  private final UserMapper userMapper;
  private final UserDetailsServiceImpl userDetailsService;

  public Page<UserView> getAll(UserFilter filter, Pageable pageable) {
    return repository.findAll(new UserSpecification(filter), pageable)
        .map(userMapper::toView);
  }

  public UserView getById(UUID userId) {
    return userMapper.toView(findById(userId));
  }

  public UserView edit(UserEditRequest userDto, UUID userId) {
    var user = findById(userId);

    userMapper.update(user, userDto);

    return userMapper.toView(repository.save(user));
  }

  public User findByEmail(String email) {
    return repository.findByEmail(email)
        .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_EMAIL, email));
  }

  public UserView me() {
    return userMapper.toView(userDetailsService.extractUserPrincipal().user());
  }

  private User findById(UUID userId) {
    return repository.findById(userId)
        .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND, userId));
  }
}
