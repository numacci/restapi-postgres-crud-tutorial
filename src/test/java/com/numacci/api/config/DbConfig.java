package com.numacci.api.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

public class DbConfig {

  @Value("${spring.datasource.username}")
  private String username;

  @Value("${spring.datasource.password}")
  private String password;

  @Value("${spring.datasource.url}")
  private String url;

  @Value("${spring.datasource.driverClassName}")
  private String jdbcDriver;

  @Bean
  public DataSource dataSource() {
    return new TransactionAwareDataSourceProxy(
        DataSourceBuilder.create()
            .username(this.username)
            .password(this.password)
            .url(this.url)
            .driverClassName(this.jdbcDriver)
            .build());
  }
}
