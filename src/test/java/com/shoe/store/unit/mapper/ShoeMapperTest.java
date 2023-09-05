package com.shoe.store.unit.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.shoe.store.dto.ShoeCatalogItem;
import com.shoe.store.enums.ProductStatus;
import com.shoe.store.mapper.ShoeMapper;
import com.shoe.store.model.shoe.Shoe;
import com.shoe.store.unit.BaseUnitTest;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

/**
 * @author Stanislav Hlova
 */

class ShoeMapperTest extends BaseUnitTest {

  private final ShoeMapper shoeMapper = Mappers.getMapper(ShoeMapper.class);

  @Test
  void shouldMapToDto_whenEntityPassed() {
    final Shoe shoe = Shoe.builder()
        .id(1L)
        .name("name")
        .price(BigDecimal.TEN)
        .productStatus(ProductStatus.IN_AVAILABLE)
        .build();
    final ShoeCatalogItem expectedDto = ShoeCatalogItem.builder()
        .id(String.valueOf(shoe.getId()))
        .name(shoe.getName())
        .price(shoe.getPrice())
        .productStatus(shoe.getProductStatus())
        .build();

    ShoeCatalogItem actualDto = shoeMapper.toDto(shoe);

    assertEquals(expectedDto, actualDto);
  }
}