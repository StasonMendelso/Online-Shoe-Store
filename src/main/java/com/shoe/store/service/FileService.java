package com.shoe.store.service;

import com.shoe.store.config.FileProperties;
import com.shoe.store.database.repository.FileRepository;
import com.shoe.store.database.repository.ShoeRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class FileService {

    private final FileRepository fileRepository;
    private final FileProperties fileProperties;
    private final ShoeRepository shoeRepository;

    public Optional<FileSystemResource> getShoeFileByFileId(Long fileId) {
        return fileRepository.findById(fileId)
                .flatMap(value -> getFileSystemResource(value.getRelativePath()))
                .or(() -> {
                    log.info("Photo file not found for file id: {}; Use default.", fileId);
                    return getDefaultShoeFile();
                });
    }

    public Optional<FileSystemResource> getMainShoeFileById(Long shoeId) {
        return shoeRepository.findMainPhotoIdByShoeId(shoeId)
                .flatMap(this::getShoeFileByFileId)
                .or(this::getDefaultShoeFile);
    }

    public Optional<FileSystemResource> getDefaultShoeFile() {
        return Optional.of(new FileSystemResource(fileProperties.getProductFileNotFoundPath()));
    }

    protected Optional<FileSystemResource> getFileSystemResource(String fileRelativePath) {
        Path fullFilePath = fileProperties.getBucket().resolve(fileRelativePath);
        if(!Files.exists(fullFilePath)){
            log.warn("File can't be found by relative path: {}", fileRelativePath);
            log.debug("File can't be found by absolute path: {}", fullFilePath);
            return Optional.empty();
        }
        return Optional.of(new FileSystemResource(fullFilePath));
    }

    @SneakyThrows
    public String getFileContentType(String path) {
        return Files.probeContentType(Path.of(path));
    }


}
