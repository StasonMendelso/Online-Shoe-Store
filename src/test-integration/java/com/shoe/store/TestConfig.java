package com.shoe.store;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;

/**
 * @author Stanislav Hlova
 */
@Data
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestConfig {
    private DatabaseConfig tests;
    @Data
    @Getter
    public static class DatabaseConfig {
        @JsonAlias("database_type")
        private String databaseType;
    }
}
