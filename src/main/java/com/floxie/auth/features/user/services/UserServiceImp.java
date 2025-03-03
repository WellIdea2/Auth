package com.floxie.auth.features.user.services;

import com.floxie.auth.features.user.dto.UserCreateRequest;
import com.floxie.auth.features.user.dto.UserEditRequest;
import com.floxie.auth.features.user.dto.UserFilter;
import com.floxie.auth.features.user.entity.User;
import com.floxie.auth.features.user.repository.UserRepository;
import com.floxie.auth.features.user.repository.UserSpecification;
import com.floxie.auth.infrastructure.config.security.utils.SecurityUtils;
import com.floxie.auth.infrastructure.mappers.UserMapper;
import com.floxie.auth.infrastructure.rabbitmq.RabbitMqProducer;
import lombok.RequiredArgsConstructor;
import org.commons.exceptions.throwable.NotFoundException;
import org.commons.feature.user.dto.UserView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.floxie.auth.infrastructure.exceptions.ExceptionMessages.USER_NOT_FOUND;
import static com.floxie.auth.infrastructure.exceptions.ExceptionMessages.USER_NOT_FOUND_EMAIL;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

  private final UserRepository repository;
  private final UserMapper userMapper;
  private final RabbitMqProducer rabbitMqProducer;

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

  @Override
  public UserView create(UserCreateRequest userDto) {
    var entity = userMapper.toEntity(userDto);

    return userMapper.toView(repository.save(entity));
  }

  public void delete(UUID userId) {
    repository.deleteById(userId);

    rabbitMqProducer.delete(userId);
  }

  public User findByEmail(String email) {
    return repository.findByEmail(email)
        .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_EMAIL, email));
  }

  public UserView me() {
    return userMapper.toView(SecurityUtils.extractLoggedUser().user());
  }

  private User findById(UUID userId) {
    return repository.findById(userId)
        .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND, userId));
  }
}
