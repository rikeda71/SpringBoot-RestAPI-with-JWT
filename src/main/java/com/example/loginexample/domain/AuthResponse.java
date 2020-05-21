package com.example.loginexample.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {

  private Long userId;

  private String name;

  private String token;

}
