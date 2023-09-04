package com.shoe.store.service;

import com.shoe.store.database.repository.ShoeRepository;
import com.shoe.store.model.shoe.Shoe;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Stanislav Hlova
 */
@Service
@RequiredArgsConstructor
public class ShoeService {

  private final ShoeRepository shoeRepository;

  public Page<Shoe> getShoesByPage(Pageable pageable) {
    return shoeRepository.findAllBy(pageable);
  }
}
