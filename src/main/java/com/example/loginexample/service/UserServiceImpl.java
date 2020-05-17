package com.example.loginexample.service;

import com.example.loginexample.domain.User;
import com.example.loginexample.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User findById(Long userId) {
    return this.userRepository.findById(userId);
  }
}
