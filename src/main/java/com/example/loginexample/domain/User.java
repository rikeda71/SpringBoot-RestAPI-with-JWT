package com.example.loginexample.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class User {

  private Long userId;

  private String name;

  private String password;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  private Long lockVersion;

  private Boolean deleteFlag;
}
