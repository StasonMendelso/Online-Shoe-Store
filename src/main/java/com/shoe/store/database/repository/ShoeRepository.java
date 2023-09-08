package com.shoe.store.database.repository;

import com.shoe.store.database.repository.custom.CustomShoeRepository;
import com.shoe.store.model.shoe.Shoe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Stanislav Hlova
 */
@Repository
public interface ShoeRepository extends PagingAndSortingRepository<Shoe, Long>, JpaRepository<Shoe, Long>, CustomShoeRepository {
    Page<Shoe> findAllBy(Pageable pageable);

    List<Shoe> findAll();
}
