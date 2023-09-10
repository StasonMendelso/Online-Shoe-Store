package com.shoe.store.database.converter;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.shoe.store.enums.ProductStatus;
import com.shoe.store.BaseUnitTest;
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