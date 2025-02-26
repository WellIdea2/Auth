package com.auth.features.user.services;

import java.util.UUID;
import com.auth.features.user.dto.UserFilter;
import com.auth.features.user.entity.User;
import com.auth.features.user.dto.UserEditRequest;
import org.commons.feature.user.dto.UserView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

  Page<UserView> getAll(UserFilter filter, Pageable pageable);

  UserView getById(UUID userId);

  UserView edit(UserEditRequest userDto, UUID userId);

  User findByEmail(String email);

  UserView me();
}
