package com.example.loginexample.service;

import com.example.loginexample.domain.User;

public interface UserService {

  User findById(Long userId);

  User findByName(String name);

  void add(User user);

  void set(User user);

  void remove(Long userId);
}
