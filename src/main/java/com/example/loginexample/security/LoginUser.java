package com.example.loginexample.security;

import java.util.Collection;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * Spring Security の UserDetails を返却するのに必要なログインユーザの雛形
 */
public class LoginUser extends User {

  @Getter
  @Setter
  private Long userId;

  @Getter
  @Setter
  private String name;

  @Getter
  @Setter
  private String password;

  /**
   * コンストラクタ
   */
  public LoginUser(
      String name,
      String password,
      Collection<? extends GrantedAuthority> authorities,
      String username) {
    super(username, password, authorities);
    this.name = name;
    this.password = password;
  }

}
