package com.example.demo.domain.user;

import com.example.demo.core.generic.AbstractService;
import org.hibernate.sql.Update;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.UUID;

public interface UserService extends UserDetailsService, AbstractService<User> {
  User register(User user);

  User registerUser(User user);

  Boolean isUserAuthenticated();

  User updateUserById(UUID id, User user) throws UsernameNotFoundException;
}
