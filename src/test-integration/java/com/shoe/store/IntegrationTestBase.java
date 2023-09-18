package com.shoe.store;

import com.shoe.store.annotation.IntegrationTest;
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
@WithMockUser(username = "test@gmail.com", password = "data", authorities = {"ADMIN", "USER"})
public abstract class IntegrationTestBase {
    private static final MySQLContainer<?> MY_SQL_CONTAINER = new MySQLContainer<>("mysql:8.0.31")
            .withUsername("root")
            .withPassword("root")
            .withEnv("MYSQL_ROOT_PASSWORD", "root")
            .withDatabaseName("online_shoe_store_dev");

    static {
        if (PropertyIdentifier.getDatabaseType() == PropertyIdentifier.DatabaseType.TEST_CONTAINERS) {
            MY_SQL_CONTAINER.setPortBindings(List.of("65500:3306"));
            MY_SQL_CONTAINER.start();
        }
    }

    @DynamicPropertySource
    public static void mySqlProperties(DynamicPropertyRegistry registry) {
        if (PropertyIdentifier.getDatabaseType() == PropertyIdentifier.DatabaseType.TEST_CONTAINERS) {
            registry.add("spring.datasource.url", MY_SQL_CONTAINER::getJdbcUrl);
            registry.add("spring.datasource.driver-class-name", MY_SQL_CONTAINER::getDriverClassName);
            registry.add("spring.datasource.username", MY_SQL_CONTAINER::getUsername);
            registry.add("spring.datasource.password", MY_SQL_CONTAINER::getPassword);
        }
    }
}
