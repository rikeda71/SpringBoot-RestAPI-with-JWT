package com.example.loginexample.controller;

import com.example.loginexample.domain.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthRestController {

  @GetMapping(path = "")
  public User getAuthUser(@AuthenticationPrincipal User user) {
    return user;
  }
}
