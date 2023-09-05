package com.shoe.store.unit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.shoe.store.database.repository.ShoeRepository;
import com.shoe.store.model.shoe.Shoe;
import com.shoe.store.service.ShoeService;
import com.shoe.store.unit.BaseUnitTest;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * @author Stanislav Hlova
 */
class ShoeServiceTest extends BaseUnitTest {

  @InjectMocks
  private ShoeService shoeService;
  @Mock
  private ShoeRepository shoeRepository;

  @Test
  void shouldReturnEmptyPage_whenNoShoesFound() {
    final Pageable pageable = PageRequest.of(0, 10);
    when(shoeRepository.findAllBy(pageable)).thenReturn(Page.empty());

    final Page<Shoe> shoesByPage = shoeService.getShoesByPage(pageable);

    assertTrue(shoesByPage.isEmpty());
    assertEquals(0, shoesByPage.getTotalElements());
  }
  @Test
  void shouldReturnPageWithFiveElements_whenFiveShoesFound() {
    final int size = 5;
    final int total = 13;
    final Pageable pageable = PageRequest.of(0, size);
    final List<Shoe> shoeList = IntStream.rangeClosed(1,size).mapToObj(value -> new Shoe()).toList();
    when(shoeRepository.findAllBy(pageable)).thenReturn(new PageImpl<>(shoeList, pageable,total));

    final Page<Shoe> shoesByPage = shoeService.getShoesByPage(pageable);

    assertFalse(shoesByPage.isEmpty());
    assertEquals(size, shoesByPage.getPageable().getPageSize());
    assertEquals(total, shoesByPage.getTotalElements());
  }

}