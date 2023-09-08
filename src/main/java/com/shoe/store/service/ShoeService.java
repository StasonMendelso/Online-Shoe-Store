package com.shoe.store.service;

import com.shoe.store.database.repository.ShoeRepository;
import com.shoe.store.model.shoe.Shoe;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Stanislav Hlova
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ShoeService {

  private final ShoeRepository shoeRepository;

  public Page<Shoe> getShoesByPage(Pageable pageable) {
    return shoeRepository.findAllBy(pageable);
  }

  public Optional<Shoe> getShoeById(Long id) {
    return shoeRepository.findById(id);
  }
  public List<Shoe> getAllShoeColorsById(Long id){
    return shoeRepository.findAllShoeColorsById(id);
  }
}
