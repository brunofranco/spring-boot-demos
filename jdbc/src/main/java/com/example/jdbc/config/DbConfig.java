package com.example.jdbc.config;

import com.example.jdbc.data.entity.CustomerEntity;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.relational.core.mapping.event.BeforeSaveEvent;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.UUID;

@Configuration
@EnableJdbcRepositories("com.example.jdbc.data")
public class DbConfig extends AbstractJdbcConfiguration {

  @Bean
  NamedParameterJdbcOperations operations() {
    return new NamedParameterJdbcTemplate(dataSource());
  }

  @Bean
  PlatformTransactionManager transactionManager() {
    return new DataSourceTransactionManager(dataSource());
  }

  @Bean
  DataSource dataSource() {
    return new EmbeddedDatabaseBuilder()
        .generateUniqueName(true)
        .setType(EmbeddedDatabaseType.H2)
        .addScript("ddl.sql")
        .build();
  }

  @Bean
  public ApplicationListener<?> idSetting() {
    return (ApplicationListener<BeforeSaveEvent>)
        event -> {
          if (event.getEntity() instanceof CustomerEntity) {
            if (((CustomerEntity) event.getEntity()).getId() == null)
              ((CustomerEntity) event.getEntity()).setId(UUID.randomUUID());
          }
        };
  }
}
