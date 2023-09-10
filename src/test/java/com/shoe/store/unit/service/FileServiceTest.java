package com.shoe.store.unit.service;

import com.shoe.store.config.FileProperties;
import com.shoe.store.database.repository.FileRepository;
import com.shoe.store.database.repository.ShoeRepository;
import com.shoe.store.service.FileService;
import com.shoe.store.unit.BaseUnitTest;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.core.io.FileSystemResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

/**
 * @author Stanislav Hlova
 */
class FileServiceTest extends BaseUnitTest {
    @InjectMocks
    private FileService fileService;

    @Mock
    private FileRepository fileRepository;
    @Mock
    private FileProperties fileProperties;
    @Mock
    private ShoeRepository shoeRepository;

    private static Path path = testDirectory.resolve("FileServiceTest");


    @BeforeAll
    static void beforeAll() throws IOException {
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
    }

    @BeforeEach
    void setUp() {
    }

    @AfterAll
    static void afterAll() throws IOException {
        Files.deleteIfExists(path);
    }

    @Test
    void shouldReturnDefaultFile_whenDefaultFilePresented() throws IOException {
        final Path defaultFilePath = testDataDirectory.resolve("1_1.jpg");
        when(fileProperties.getProductFileNotFoundPath()).thenReturn(defaultFilePath);

        Optional<FileSystemResource> resourceOptional = fileService.getDefaultProductFile();

        assertAll(() -> {
            assertTrue(resourceOptional.isPresent());
            FileSystemResource resource = resourceOptional.get();
            assertEquals(defaultFilePath, resource.getFile().toPath());
            assertEquals(Files.size(defaultFilePath), resource.getFile().length());
            assertArrayEquals(Files.readAllBytes(defaultFilePath), resource.getContentAsByteArray());
        });

    }
}
