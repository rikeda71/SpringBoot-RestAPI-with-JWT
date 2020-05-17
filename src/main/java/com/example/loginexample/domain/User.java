package com.example.loginexample.domain;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class User {

  private Long userId;

  private String name;

  private String password;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  private Long lockVersion;

  private Boolean deleteFlag;
}
