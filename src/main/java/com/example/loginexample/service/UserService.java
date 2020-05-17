package com.example.loginexample.service;

import com.example.loginexample.domain.User;

public interface UserService {

  public User findById(Long userId);

}
