package com.shoe.store.controller;

import com.shoe.store.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Stanislav Hlova
 */
@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @GetMapping("/shoe/{fileId}")
    @ResponseBody
    public ResponseEntity<Resource> getShoePhoto(@PathVariable(value = "fileId") Long fileId) {
        return fileService.getShoeFileByFileId(fileId)
                .map(resource -> ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(fileService.getFileContentType(resource.getPath())))
                        .body((Resource) resource))
                .orElseGet(ResponseEntity.notFound()::build);
    }
    @GetMapping("/shoe/{shoeId}/main")
    @ResponseBody
    public ResponseEntity<Resource> getMainShoePhoto(@PathVariable(value = "shoeId") Long shoeId) {
        return fileService.getMainShoeFileById(shoeId)
                .map(resource -> ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(fileService.getFileContentType(resource.getPath())))
                        .body((Resource) resource))
                .orElseGet(ResponseEntity.notFound()::build);
    }
}
