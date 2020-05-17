package com.example.loginexample.security;

import com.example.loginexample.domain.User;
import com.example.loginexample.repository.UserRepository;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private UserRepository userRepository;

  public UserDetailsServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    // SecurityConfigに設定されているユーザの名前が引数に渡される
    User user = userRepository.findByName(s);
    if (user == null) {
      throw new UsernameNotFoundException(String.format("user `%s` not found", s));
    }

    System.out.println(user);

    return new LoginUser(
        user.getName(),
        user.getPassword(),
        this.decisionRoles(user.getName().contains("admin")),
        user.getName()
    );
  }

  private List<GrantedAuthority> decisionRoles(boolean isAdmin) {
    return isAdmin ? UserRole.ADMIN.getGrantedAuthority() : UserRole.USER.getGrantedAuthority();
  }
}
