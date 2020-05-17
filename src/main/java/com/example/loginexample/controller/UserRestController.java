package com.example.loginexample.controller;

import com.example.loginexample.domain.User;
import com.example.loginexample.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("services/v1/user")
public class UserRestController {

  private final UserService userService;

  private final PasswordEncoder passwordEncoder;

  public UserRestController(UserService userService,
                            PasswordEncoder passwordEncoder) {
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
  }

  @GetMapping(path = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public User get(@PathVariable Long userId) {
    return this.userService.findById(userId);
  }

  @PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
  public void add(@RequestBody User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    this.userService.add(user);
  }

  @PatchMapping(path = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public void set(@PathVariable Long userId, @RequestBody User user) {
    User oldUser = this.userService.findById(userId);
    // パスワードを変更した場合，ハッシュ化する
    if (oldUser.getPassword().equals(user.getPassword())) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
    }
    this.userService.set(user);
  }

  @DeleteMapping(path = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public void remove(@PathVariable Long userId) {
    this.userService.remove(userId);
  }
}
