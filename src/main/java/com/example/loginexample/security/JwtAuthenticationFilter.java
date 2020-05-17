package com.example.loginexample.security;

import com.example.loginexample.domain.UserForm;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Authentication Filter
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private AuthenticationManager manager;
  private PasswordEncoder passwordEncoder;

  public JwtAuthenticationFilter(AuthenticationManager manager, PasswordEncoder passwordEncoder) {
    this.manager = manager;
    this.passwordEncoder = passwordEncoder;

    setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));

    setUsernameParameter("name");
    setPasswordParameter("password");
  }

  public Authentication attemptAuthentication(HttpServletRequest req,
                                              HttpServletResponse res) throws AuthenticationException {
    try {
      UserForm user = new ObjectMapper().readValue(req.getInputStream(), UserForm.class);

      return manager.authenticate(
          new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword(), new ArrayList<>())
      );
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) {
    String token = Jwts.builder()
        .setSubject(((User)auth.getPrincipal()).getUsername())
        .setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
        .signWith(SignatureAlgorithm.HS512, "secret".getBytes())
        .compact();
    res.addHeader("Authorization", "Bearer " + token);
  }

}
