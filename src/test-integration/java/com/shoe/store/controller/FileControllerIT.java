package com.shoe.store.controller;

import com.shoe.store.IntegrationTestBase;
import com.shoe.store.service.FileService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.doReturn;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

/**
 * @author Stanislav Hlova
 */
@RequiredArgsConstructor
class FileControllerIT extends IntegrationTestBase {
    private final MockMvc mockMvc;
    private final Environment environment;

    @SpyBean
    private final FileService fileService;

    @Test
    void shouldReturnOk_whenPhotoNotFoundPresented() throws Exception {
        final String pathToDefaultPhoto = environment.getRequiredProperty("app.file.product-file-not-found-path");

        Path path = Path.of(pathToDefaultPhoto);
        mockMvc.perform(get("/files/shoe/photo-not-found"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.parseMediaType(Files.probeContentType(path))))
                .andExpect(content().bytes(Files.readAllBytes(path)))
                .andExpect(header().string(HttpHeaders.CONTENT_LENGTH, String.valueOf(Files.readAllBytes(path).length)))
                .andExpect(header().string(HttpHeaders.ACCEPT_RANGES, "bytes"));
    }

    @Test
    void shouldReturnNotFound_whenPhotoNotFoundNotPresented() throws Exception {
        doReturn(Optional.empty()).when(fileService).getDefaultShoeFile();

        mockMvc.perform(get("/files/shoe/photo-not-found"))
                .andExpect(status().isNotFound());
    }



    @Test
    void shouldReturnOk_whenMainShoePhotoPresented() throws Exception {
        final String pathToDefaultPhoto = environment.getRequiredProperty("app.file.product-file-not-found-path");

        Path path = Path.of(pathToDefaultPhoto);
        mockMvc.perform(get("/files/shoe/1/main"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.parseMediaType(Files.probeContentType(path))))
                .andExpect(content().bytes(Files.readAllBytes(path)))
                .andExpect(header().string(HttpHeaders.CONTENT_LENGTH, String.valueOf(Files.readAllBytes(path).length)))
                .andExpect(header().string(HttpHeaders.ACCEPT_RANGES, "bytes"));
    }
    @Test
    void shouldReturnDefaultPhoto_whenMainShoePhotoNotPresented() throws Exception {
        final String pathToBucket = environment.getRequiredProperty("app.file.bucket");

        Path path = Path.of(pathToBucket).resolve("1_1.jpg");
        mockMvc.perform(get("/files/shoe/-1/main"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.parseMediaType(Files.probeContentType(path))))
                .andExpect(content().bytes(Files.readAllBytes(path)))
                .andExpect(header().string(HttpHeaders.CONTENT_LENGTH, String.valueOf(Files.readAllBytes(path).length)))
                .andExpect(header().string(HttpHeaders.ACCEPT_RANGES, "bytes"));
    }

    @Test
    void shouldReturnNotFound_whenMainShoePhotoNotPresented() throws Exception {
        doReturn(Optional.empty()).when(fileService).getMainShoeFileById(1L);

        mockMvc.perform(get("/files/shoe/1/main"))
                .andExpect(status().isNotFound());
    }
    @Test
    void shouldReturnOk_whenShoePhotoPresented() throws Exception {
        final String pathToBucket = environment.getRequiredProperty("app.file.bucket");

        Path path = Path.of(pathToBucket).resolve("1_1.jpg");
        mockMvc.perform(get("/files/shoe/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.parseMediaType(Files.probeContentType(path))))
                .andExpect(content().bytes(Files.readAllBytes(path)))
                .andExpect(header().string(HttpHeaders.CONTENT_LENGTH, String.valueOf(Files.readAllBytes(path).length)))
                .andExpect(header().string(HttpHeaders.ACCEPT_RANGES, "bytes"));
    }

    @Test
    void shouldReturnNotFound_whenShoePhotoNotPresented() throws Exception {
        doReturn(Optional.empty()).when(fileService).getShoeFileByFileId(1L);

        mockMvc.perform(get("/files/shoe/1"))
                .andExpect(status().isNotFound());
    }
}
