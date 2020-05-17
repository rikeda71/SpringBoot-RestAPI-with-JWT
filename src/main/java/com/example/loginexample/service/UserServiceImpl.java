package com.example.loginexample.service;

import com.example.loginexample.domain.User;
import com.example.loginexample.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User findById(Long userId) {
    return this.userRepository.findById(userId);
  }

  @Override
  public void add(User user) {
    this.userRepository.insert(user);
  }

  @Override
  public void set(User user) {
    this.userRepository.update(user);
  }

  @Override
  public void remove(Long userId) {
    this.userRepository.delete(userId);
  }
}
