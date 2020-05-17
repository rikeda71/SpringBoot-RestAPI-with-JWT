package com.example.loginexample.repository;

import com.example.loginexample.domain.User;

public interface UserRepository {

  public User findById(Long userId);

  public void insert(User user);

  public void update(User user);

  public void delete(Long userId);

}
