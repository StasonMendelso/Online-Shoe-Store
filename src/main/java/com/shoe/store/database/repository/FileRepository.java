package com.shoe.store.database.repository;

import com.shoe.store.model.file.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Stanislav Hlova
 */
@Repository
public interface FileRepository extends JpaRepository<File,Long> {
}
