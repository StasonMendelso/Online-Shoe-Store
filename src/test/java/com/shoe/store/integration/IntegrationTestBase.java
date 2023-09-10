package com.shoe.store.integration;

import com.shoe.store.integration.annotation.IntegrationTest;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.MySQLContainer;

import java.util.List;

/**
 * @author Stanislav Hlova
 */
@IntegrationTest
@Sql(scripts = "classpath:sql/data.sql")
@WithMockUser(username = "test@gmail.com", password = "data",authorities = {"ADMIN", "USER"})
public abstract class IntegrationTestBase {

  private static final MySQLContainer<?> container = new MySQLContainer<>("mysql:8.0.31")
      .withUsername("root")
      .withPassword("root")
      .withEnv("MYSQL_ROOT_PASSWORD", "root")
      .withDatabaseName("online_shoe_store_dev");

  @BeforeAll
  public static void runContainer() {
    container.setPortBindings(List.of("65500:3306"));
    container.start();
  }
  @DynamicPropertySource
  public static void mySqlProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", container::getJdbcUrl);
    registry.add("spring.datasource.driver-class-name", container::getDriverClassName);
    registry.add("spring.datasource.username", container::getUsername);
    registry.add("spring.datasource.password", container::getPassword);
  }
}
