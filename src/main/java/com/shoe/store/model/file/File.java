package com.shoe.store.model.file;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author Stanislav Hlova
 */
@Entity
@Table(name = "files")
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class File {
    @Id
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "relative_path", unique = true, nullable = false)
    private String relativePath;

    @ManyToOne
    @JoinColumn(name = "file_extensions_id",referencedColumnName = "id")
    private FileExtension fileExtension;


}
