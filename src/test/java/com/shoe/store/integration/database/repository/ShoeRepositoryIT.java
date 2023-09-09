package com.shoe.store.integration.database.repository;

import com.shoe.store.database.repository.ShoeRepository;
import com.shoe.store.integration.IntegrationTestBase;
import com.shoe.store.model.shoe.Shoe;
import lombok.AllArgsConstructor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * @author Stanislav Hlova
 */
@AllArgsConstructor
class ShoeRepositoryIT extends IntegrationTestBase {

  private final ShoeRepository shoeRepository;

  @Test
  void shouldReturnTwentyEntities_whenFindAllCalled() {
    final int expectedCount = 20;

    List<Shoe> shoeList = shoeRepository.findAll();

    assertEquals(expectedCount, shoeList.size());
  }

  @Test
  void shouldReturnFiveEntitiesOnSecondPage_whenFiveAsked() {
    final int expectedElementsNumber = 5;
    final int pageNumber = 1;

    Page<Shoe> shoePage = shoeRepository.findAllBy(
        PageRequest.of(pageNumber, expectedElementsNumber));

    assertEquals(pageNumber, shoePage.getPageable().getPageNumber());
    assertEquals(expectedElementsNumber, shoePage.getPageable().getPageSize());
  }


  @Test
  void shouldReturnTwoSiblingsColor_whenTwoSiblingsPersisted(){
    final Long id = 1L;

    List<Shoe> shoeSiblingsById = shoeRepository.findAllShoeColorsById(id);


  }
}