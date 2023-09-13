package com.shoe.store.controller;

import com.shoe.store.BaseUnitTest;
import com.shoe.store.service.FileService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

/**
 * @author Stanislav Hlova
 */
class FileControllerTest extends BaseUnitTest {
    @InjectMocks
    private FileController fileController;

    @Mock
    private FileService fileService;
    @Mock
    private FileSystemResource fileSystemResource;

    @Test
    void shouldReturnOK_whenDefaultShoePhotoFound() {
        final MediaType mediaType = MediaType.IMAGE_JPEG;
        final String path = "/path";
        when(fileService.getDefaultShoeFile()).thenReturn(Optional.of(fileSystemResource));
        when(fileSystemResource.getPath()).thenReturn(path);
        when(fileService.getFileContentType(fileSystemResource.getPath())).thenReturn(mediaType.toString());

        ResponseEntity<Resource> actual = fileController.getShoesPhotoNotFound();

        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertEquals(mediaType, actual.getHeaders().getContentType());
        assertEquals(fileSystemResource, actual.getBody());

    }

    @Test
    void shouldReturnNotFound_whenDefaultShoePhotoNotFound() {
        when(fileService.getDefaultShoeFile()).thenReturn(Optional.empty());

        ResponseEntity<Resource> actual = fileController.getShoesPhotoNotFound();

        assertEquals(HttpStatus.NOT_FOUND, actual.getStatusCode());
    }

    @Test
    void shouldReturnNotFound_whenMainShoePhotoNotFound() {
        final Long shoeId = 1L;
        when(fileService.getMainShoeFileById(shoeId)).thenReturn(Optional.empty());

        ResponseEntity<Resource> actual = fileController.getMainShoePhoto(shoeId);

        assertEquals(HttpStatus.NOT_FOUND, actual.getStatusCode());
    }

    @Test
    void shouldReturnOK_whenMainShoePhotoFound() {
        final MediaType mediaType = MediaType.IMAGE_JPEG;
        final String path = "/path";
        final Long shoeId = 1L;
        when(fileService.getMainShoeFileById(shoeId)).thenReturn(Optional.of(fileSystemResource));
        when(fileSystemResource.getPath()).thenReturn(path);
        when(fileService.getFileContentType(fileSystemResource.getPath())).thenReturn(mediaType.toString());

        ResponseEntity<Resource> actual = fileController.getMainShoePhoto(shoeId);

        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertEquals(mediaType, actual.getHeaders().getContentType());
        assertEquals(fileSystemResource, actual.getBody());

    }

    @Test
    void shouldReturnOK_whenShoePhotoFound() {
        final MediaType mediaType = MediaType.IMAGE_JPEG;
        final String path = "/path";
        final Long fileId = 1L;
        when(fileService.getShoeFileByFileId(fileId)).thenReturn(Optional.of(fileSystemResource));
        when(fileSystemResource.getPath()).thenReturn(path);
        when(fileService.getFileContentType(fileSystemResource.getPath())).thenReturn(mediaType.toString());

        ResponseEntity<Resource> actual = fileController.getShoePhoto(fileId);

        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertEquals(mediaType, actual.getHeaders().getContentType());
        assertEquals(fileSystemResource, actual.getBody());

    }
    @Test
    void shouldReturnNotFound_whenShoePhotoNotFound() {
        final Long fileId = 1L;
        when(fileService.getShoeFileByFileId(fileId)).thenReturn(Optional.empty());

        ResponseEntity<Resource> actual = fileController.getShoePhoto(fileId);

        assertEquals(HttpStatus.NOT_FOUND, actual.getStatusCode());
    }
}