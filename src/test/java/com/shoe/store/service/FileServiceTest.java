package com.shoe.store.service;

import com.shoe.store.BaseUnitTest;
import com.shoe.store.config.FileProperties;
import com.shoe.store.database.repository.FileRepository;
import com.shoe.store.database.repository.ShoeRepository;
import com.shoe.store.model.file.File;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.Spy;
import org.springframework.core.io.FileSystemResource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

/**
 * @author Stanislav Hlova
 */
class FileServiceTest extends BaseUnitTest {
    @Spy
    @InjectMocks
    private FileService fileService;

    @Mock
    private FileRepository fileRepository;
    @Mock
    private FileProperties fileProperties;
    @Mock
    private ShoeRepository shoeRepository;

    @Test
    void shouldReturnDefaultFile_whenDefaultFilePresented() {
        final Path defaultFilePath = testDataDirectory.resolve("1_1.jpg");
        when(fileProperties.getProductFileNotFoundPath()).thenReturn(defaultFilePath);

        Optional<FileSystemResource> resourceOptional = fileService.getDefaultShoeFile();

        assertAll(() -> {
            assertTrue(resourceOptional.isPresent());
            FileSystemResource resource = resourceOptional.get();
            assertEquals(defaultFilePath, resource.getFile().toPath());
            assertEquals(Files.size(defaultFilePath), resource.getFile().length());
            assertArrayEquals(Files.readAllBytes(defaultFilePath), resource.getContentAsByteArray());
        });
    }

    @Test
    void shouldReturnMockedResource_whenResourcePresented() {
        try (MockedStatic<Files> filesMockedStatic = mockStatic(Files.class);
             MockedConstruction<FileSystemResource> mockedConstruction = mockConstruction(FileSystemResource.class)) {
            final Path bucket = mock(Path.class);
            final String fileRelativePath = "/relative_path";
            final Path absolutePath = mock(Path.class);
            when(fileProperties.getBucket()).thenReturn(bucket);
            when(bucket.resolve(fileRelativePath)).thenReturn(absolutePath);
            filesMockedStatic.when(() -> Files.exists(absolutePath)).thenReturn(Boolean.TRUE);

            Optional<FileSystemResource> expected = fileService.getFileSystemResource(fileRelativePath);

            List<FileSystemResource> constructed = mockedConstruction.constructed();
            assertEquals(1, constructed.size());
            assertTrue(expected.isPresent());
            assertEquals(constructed.get(0), expected.get());
            filesMockedStatic.verify(() -> Files.exists(absolutePath), only());
            verify(fileProperties, only()).getBucket();
        }

    }

    @Test
    void shouldReturnEmptyOptional_whenResourceNotPresented() {
        try (MockedStatic<Files> filesMockedStatic = mockStatic(Files.class);
             MockedConstruction<FileSystemResource> mockedConstruction = mockConstruction(FileSystemResource.class)) {
            final Path bucket = mock(Path.class);
            final String fileRelativePath = "/relative_path";
            final Path absolutePath = mock(Path.class);
            when(fileProperties.getBucket()).thenReturn(bucket);
            when(bucket.resolve(fileRelativePath)).thenReturn(absolutePath);
            filesMockedStatic.when(() -> Files.exists(absolutePath)).thenReturn(Boolean.FALSE);

            Optional<FileSystemResource> expected = fileService.getFileSystemResource(fileRelativePath);

            List<FileSystemResource> constructed = mockedConstruction.constructed();
            assertEquals(0, constructed.size());
            assertTrue(expected.isEmpty());
            assertEquals(Optional.empty(), expected);
            filesMockedStatic.verify(() -> Files.exists(absolutePath), only());
            verify(fileProperties, only()).getBucket();
        }

    }

    @Test
    void shouldReturnShoeFileById_whenShoeFilePresented() {
        final Long fileId = 1L;
        final File file = mock(File.class);
        final String relativePath = "/relative_path";
        final FileSystemResource expected = mock(FileSystemResource.class);
        when(fileRepository.findById(fileId)).thenReturn(Optional.of(file));
        when(file.getRelativePath()).thenReturn(relativePath);
        doReturn(Optional.of(expected)).when(fileService).getFileSystemResource(relativePath);

        Optional<FileSystemResource> actual = fileService.getShoeFileByFileId(fileId);

        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

    @Test
    void shouldReturnDefaultFile_whenShoeFileByIdNotPresented() {
        final Long fileId = 1L;
        final FileSystemResource expected = mock(FileSystemResource.class);
        when(fileRepository.findById(fileId)).thenReturn(Optional.empty());
        doReturn(Optional.of(expected)).when(fileService).getDefaultShoeFile();

        Optional<FileSystemResource> actual = fileService.getShoeFileByFileId(fileId);

        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

    @Test
    void shouldReturnShoeMainFileByShoeId_whenMainFilePresented() {
        final Long fileId = 1L;
        final Long shoeId = 2L;
        final FileSystemResource expected = mock(FileSystemResource.class);
        when(shoeRepository.findMainPhotoIdByShoeId(shoeId)).thenReturn(Optional.of(fileId));
        doReturn(Optional.of(expected)).when(fileService).getShoeFileByFileId(fileId);

        Optional<FileSystemResource> actual = fileService.getMainShoeFileById(shoeId);

        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

    @Test
    void shouldReturnDefaultShoePhoto_whenMainFileNotPresented() {
        final Long shoeId = 2L;
        final FileSystemResource expected = mock(FileSystemResource.class);
        when(shoeRepository.findMainPhotoIdByShoeId(shoeId)).thenReturn(Optional.empty());
        doReturn(Optional.of(expected)).when(fileService).getDefaultShoeFile();

        Optional<FileSystemResource> actual = fileService.getMainShoeFileById(shoeId);

        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }
}
