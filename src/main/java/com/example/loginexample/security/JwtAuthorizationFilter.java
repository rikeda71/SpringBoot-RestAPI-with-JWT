package com.example.loginexample.security;

import com.example.loginexample.service.UserService;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

  private AuthenticationManager authenticationManager;
  private UserService userService;

  public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserService userService) {
    super(authenticationManager);
    this.userService = userService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain chain) throws IOException, ServletException {

    String header = request.getHeader("Authorization");

    // Authorization ヘッダーが存在しない or Bearer から始まらない
    if (header == null || !header.startsWith("Bearer ")) {
      chain.doFilter(request, response);
      return;
    }

    UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(header);

    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    chain.doFilter(request, response);
  }

  private UsernamePasswordAuthenticationToken getAuthenticationToken(String httpHeader) {
    String userString = Jwts.parser()
        .setSigningKey("secret".getBytes())
        .parseClaimsJws(httpHeader.replace("Bearer ", ""))
        .getBody()
        .getSubject();

    // insert user domain to principal
    var user = this.userService.findByName(userString);
    return userString != null ? new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>()) : null;
  }
}
