package com.floxie.auth.features.user.services;

import com.floxie.auth.features.user.dto.UserCreateRequest;
import com.floxie.auth.features.user.dto.UserEditRequest;
import com.floxie.auth.features.user.dto.UserFilter;
import com.floxie.auth.features.user.entity.User;
import java.util.UUID;
import org.commons.feature.user.dto.UserView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

  Page<UserView> getAll(UserFilter filter, Pageable pageable);

  UserView getById(UUID userId);

  UserView edit(UserEditRequest userDto, UUID userId);

  UserView create(UserCreateRequest userDto);

  void delete (UUID userId);

  User findByEmail(String email);

  UserView me();
}
