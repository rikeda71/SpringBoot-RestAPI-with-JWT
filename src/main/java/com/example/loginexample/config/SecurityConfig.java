package com.example.loginexample.config;

import com.example.loginexample.security.JwtAuthenticationFilter;
import com.example.loginexample.security.JwtAuthorizationFilter;
import com.example.loginexample.security.UserDetailsServiceImpl;
import com.example.loginexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
    prePostEnabled = true,
    securedEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  UserDetailsServiceImpl userDetailsService;

  @Autowired
  UserService userService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .cors().configurationSource(this.corsConfigurationSource()) // frontendとの連携のため，有効
        .and().authorizeRequests()
        .antMatchers("/login").permitAll()
        .mvcMatchers(HttpMethod.PATCH,  "/services/v1/user/**").authenticated()
        .mvcMatchers(HttpMethod.DELETE,  "/services/v1/user/**").authenticated()
        .anyRequest().permitAll()
        .and().logout()
        .and().csrf().disable() // frontendとの連携のため，無効
        .addFilter(new JwtAuthenticationFilter(authenticationManager(), passwordEncoder()))
        .addFilter(new JwtAuthorizationFilter(authenticationManager(), this.userService))
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.eraseCredentials(true)
        .userDetailsService(this.userDetailsService)
        .passwordEncoder(this.passwordEncoder());
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    // controller で bcrypt でパスワードをハッシュ化するために必要
    return new BCryptPasswordEncoder();
  }

  private CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration corsConfiguration = new CorsConfiguration();
    corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
    corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
    // http headers for jwt
    corsConfiguration.addAllowedMethod("Authorization ");
    corsConfiguration.addAllowedOrigin("http://localhost:8081");
    corsConfiguration.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
    corsSource.registerCorsConfiguration("/**", corsConfiguration);
    return corsSource;
  }
}
