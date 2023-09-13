package com.shoe.store;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.file.Path;

/**
 * @author Stanislav Hlova
 */
@ExtendWith(MockitoExtension.class)
public abstract class BaseUnitTest {
    protected static Path testDirectory = Path.of("src/test/resources/test");
    protected static Path testDataDirectory = Path.of("src/test/resources/data");
}
