package com.example.loginexample.repository;

import com.example.loginexample.domain.User;
import com.example.loginexample.repository.mapper.UserMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

  private UserMapper userMapper;

  public UserRepositoryImpl(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  @Override
  public User findById(Long userId) {
    return this.userMapper.findById(userId);
  }

  @Override
  public void insert(User user) {

  }

  @Override
  public void update(User user) {

  }

  @Override
  public void delete(Long userId) {

  }
}
