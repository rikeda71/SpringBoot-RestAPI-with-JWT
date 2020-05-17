package com.example.loginexample.controller;

import com.example.loginexample.domain.User;
import com.example.loginexample.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("services/v1/user")
public class UserRestController {

  private UserService userService;

  public UserRestController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping(path = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public User get(@PathVariable Long userId) {
    return this.userService.findById(userId);
  }
}
