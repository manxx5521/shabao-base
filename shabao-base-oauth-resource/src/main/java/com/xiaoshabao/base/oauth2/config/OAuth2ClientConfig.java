package com.xiaoshabao.base.oauth2.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import lombok.Getter;
import lombok.Setter;

@Configuration
@Setter
@Getter
public class OAuth2ClientConfig implements InitializingBean{
  
  private final JdbcTemplate jdbcTemplate;
  
  @Value("${spring.application.name:no}")
  private String clientId;
  
  private String clientSecret;
  
  private String loginUrl;
  
  @Autowired
  public OAuth2ClientConfig(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate=jdbcTemplate;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    clientSecret="123";
    loginUrl="http://localhost:8080/login";
  }
  

}
