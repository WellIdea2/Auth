package com.auth.infrastructure.mappers;

import com.auth.features.user.dto.UserCreateRequest;
import com.auth.features.user.dto.UserEditRequest;
import org.commons.feature.user.dto.UserView;
import com.auth.features.user.entity.User;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring",
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
@DecoratedWith(UserMapperDecoder.class)
public interface UserMapper {

  User toEntity(UserCreateRequest dto);

  UserView toView(User entity);

  void update(@MappingTarget User entity, UserEditRequest dto);
}
