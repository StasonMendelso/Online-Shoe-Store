package com.shoe.store.unit.database.converter;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.shoe.store.database.converter.ProductStatusConverter;
import com.shoe.store.enums.ProductStatus;
import com.shoe.store.unit.BaseUnitTest;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * @author Stanislav Hlova
 */
class ProductStatusConverterTest extends BaseUnitTest {

  private final ProductStatusConverter productStatusConverter = new ProductStatusConverter();

  @Test
  void shouldConvertToDatabaseColumnByProductStatusName() {
    assertAll(() -> Arrays.stream(ProductStatus.values()).forEach(productStatus -> {
          final String expected = productStatus.getName();

          final String actual = productStatusConverter.convertToDatabaseColumn(productStatus);

          assertEquals(expected, actual);
        })
    );

  }
  
}