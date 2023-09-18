package com.shoe.store;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

/**
 * @author Stanislav Hlova
 */
public class PropertyIdentifier {
    private static final TestConfig config;

    static {
        File file = new File("src/test-integration/resources/application-test.yaml");
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        try {
            config = objectMapper.readValue(file, TestConfig.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static DatabaseType getDatabaseType() {
        if (config.getTests().getDatabaseType().equals("test-containers")) {
            return DatabaseType.TEST_CONTAINERS;
        }
        if (config.getTests().getDatabaseType().equals("test-database")) {
            return DatabaseType.TEST_DATABASE;
        }
        throw new IllegalArgumentException("Can't detect which type of database to use in integration test.");
    }

    public enum DatabaseType {
        TEST_CONTAINERS, TEST_DATABASE
    }

}
