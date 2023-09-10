package com.shoe.store.config;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Stanislav Hlova
 */
@ConfigurationProperties(prefix = "app.file")
@Validated
@Data
public class FileProperties {
    @NotNull
    private final Path bucket;
    @NotNull
    private final Path productFileNotFoundPath;

    public FileProperties(String bucket, Path productFileNotFoundPath) {
        if (bucket.startsWith("rel:")) {
            bucket = bucket.substring(4);
        }
        Path pathToBucket = Path.of(bucket);
        if (bucket.isBlank() || bucket.equals("<your bucket name>") || !Files.isDirectory(pathToBucket)) {
            throw new InvalidPropertyException(FileProperties.class, "app.file.bucket", "You must enter a bucket! It must be a path of directory.");
        }
        if(!Files.exists(productFileNotFoundPath)){
            throw new InvalidPropertyException(FileProperties.class, "app.file.product-file-not-found-path", "You must enter an existed file!");
        }
        this.bucket = pathToBucket;
        this.productFileNotFoundPath = productFileNotFoundPath;
    }
}
