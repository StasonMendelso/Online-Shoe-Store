package com.shoe.store.database.repository;

import com.shoe.store.model.shoe.Shoe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Stanislav Hlova
 */
@Repository
public interface ShoeRepository extends PagingAndSortingRepository<Shoe,Long> {
  Page<Shoe> findAllBy(Pageable pageable);
}
