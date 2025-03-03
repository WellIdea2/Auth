package com.floxie.auth.infrastructure.config.security.services;

import com.floxie.auth.features.user.services.UserService;
import com.floxie.auth.infrastructure.config.security.dto.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private UserService service;

  @Override
  public UserDetails loadUserByUsername(String email) {
    return new CustomUserDetails(service.findByEmail(email));
  }

  @Autowired
  public void setService(@Lazy UserService service) {
    this.service = service;
  }
}
