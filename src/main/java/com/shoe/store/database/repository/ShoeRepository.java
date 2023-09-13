package com.shoe.store.database.repository;

import com.shoe.store.database.repository.custom.CustomShoeRepository;
import com.shoe.store.model.shoe.Shoe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Stanislav Hlova
 */
@Repository
public interface ShoeRepository extends PagingAndSortingRepository<Shoe, Long>, JpaRepository<Shoe, Long>, CustomShoeRepository {
    Page<Shoe> findAllBy(Pageable pageable);

    List<Shoe> findAll();

    @Query(value = "SELECT files_id " +
                   "FROM shoe_files " +
                   "WHERE shoes_id = :shoeId AND main_photo = TRUE", nativeQuery = true)
    Optional<Long> findMainPhotoIdByShoeId(Long shoeId);
}
