package com.example.loginexample.domain;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Todo {

  private Long todoId;

  private User user;

  private Integer tagId;

  private String title;

  private String detail;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  private Long lockVersion;

  private Boolean deleteFlag;

}
