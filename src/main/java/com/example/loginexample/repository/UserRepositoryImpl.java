package com.example.loginexample.repository;

import com.example.loginexample.domain.User;
import com.example.loginexample.repository.mapper.UserMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

  private final UserMapper userMapper;

  public UserRepositoryImpl(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  @Override
  public User findById(Long userId) {
    return this.userMapper.findById(userId);
  }

  @Override
  public User findByName(String name) {
    return this.userMapper.findByName(name);
  }

  @Override
  public void insert(User user) {
    this.userMapper.insert(user);
  }

  @Override
  public void update(User user) {
    this.userMapper.update(user);
  }

  @Override
  public void delete(Long userId) {
    this.userMapper.delete(userId);
  }
}
