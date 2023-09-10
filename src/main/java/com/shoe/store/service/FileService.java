package com.shoe.store.service;

import com.shoe.store.config.FileProperties;
import com.shoe.store.database.repository.FileRepository;
import com.shoe.store.database.repository.ShoeRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

/**
 * @author Stanislav Hlova
 */
@Service
@RequiredArgsConstructor
@Transactional
public class FileService {

    private final FileRepository fileRepository;
    private final FileProperties fileProperties;
    private final ShoeRepository shoeRepository;

    public Optional<FileSystemResource> getShoeFileByFileId(Long fileId) {
        return fileRepository.findById(fileId)
                .flatMap(value -> getFileBytes(value.getRelativePath()))
                .or(this::getDefaultProductFile);
    }
    public Optional<FileSystemResource> getMainShoeFileById(Long shoeId) {
        return shoeRepository.findMainPhotoIdByShoeId(shoeId)
                .flatMap(this::getShoeFileByFileId)
                .or(this::getDefaultProductFile);
    }
    public Optional<FileSystemResource> getDefaultProductFile() {
        return Optional.of(new FileSystemResource(fileProperties.getProductFileNotFoundPath()));
    }

    protected Optional<FileSystemResource> getFileBytes(String fileRelativePath) {
        Path fullFilePath = fileProperties.getBucket().resolve(fileRelativePath);

        return Files.exists(fullFilePath) ? Optional.of(new FileSystemResource(fullFilePath)) : Optional.empty();
    }

    @SneakyThrows
    public String getFileContentType(String path) {
        return Files.probeContentType(Path.of(path));
    }


}
