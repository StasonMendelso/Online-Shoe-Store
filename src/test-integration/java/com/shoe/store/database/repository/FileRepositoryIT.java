package com.shoe.store.database.repository;

import com.shoe.store.model.file.File;
import com.shoe.store.model.file.FileExtension;
import lombok.AllArgsConstructor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.shoe.store.IntegrationTestBase;
import java.util.Optional;

/**
 * @author Stanislav Hlova
 */
@AllArgsConstructor
class FileRepositoryIT extends IntegrationTestBase {
    private final FileRepository fileRepository;

    @Test
    void shouldReturnFileById_whenFilePresented() {
        final Long id = 1L;
        final File expected = File.builder()
                .id(id)
                .name("1_1")
                .relativePath("shoes/1_1.jpg")
                .fileExtension(FileExtension.builder()
                        .extension("jpg")
                        .id(1L)
                        .build())
                .build();

        Optional<File> actual = fileRepository.findById(id);

        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }
    @Test
    void shouldReturnEmptyById_whenFileNotPresented() {
        final Long id = -1L;

        Optional<File> actual = fileRepository.findById(id);

        assertTrue(actual.isEmpty());
    }

}
