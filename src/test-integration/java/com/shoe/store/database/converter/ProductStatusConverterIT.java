package com.shoe.store.database.converter;

import com.shoe.store.enums.ProductStatus;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import com.shoe.store.IntegrationTestBase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author Stanislav Hlova
 */
@RequiredArgsConstructor
class ProductStatusConverterIT extends IntegrationTestBase {

  private final EntityManager entityManager;
  private final ProductStatusConverter productStatusConverter = new ProductStatusConverter();

  @Test
  void shouldConvertAllProductStatusFromDatabase() {
    String allEnumValues = (String) entityManager.createNativeQuery("SELECT\n"
        + "  TRIM('enum(' FROM TRIM(')' FROM COLUMN_TYPE)) AS enum_values\n"
        + "FROM INFORMATION_SCHEMA.COLUMNS\n"
        + "WHERE TABLE_SCHEMA = 'online_shoe_store_dev'\n"
        + "  AND TABLE_NAME = 'shoes'\n"
        + "  AND COLUMN_NAME = 'product_status';").getSingleResult();
    Arrays.stream(allEnumValues.split(","))
        .map(s -> s.substring(1, s.length() - 1)).forEach((value) -> {
          ProductStatus productStatus = productStatusConverter.convertToEntityAttribute(value);
          assertNotNull(productStatus);
        });
  }

  @Test
  void shouldConvertAllProductStatusToDatabase() {
    String allEnumValues = (String) entityManager.createNativeQuery("SELECT\n"
        + "  TRIM('enum(' FROM TRIM(')' FROM COLUMN_TYPE)) AS enum_values\n"
        + "FROM INFORMATION_SCHEMA.COLUMNS\n"
        + "WHERE TABLE_SCHEMA = 'online_shoe_store_dev'\n"
        + "  AND TABLE_NAME = 'shoes'\n"
        + "  AND COLUMN_NAME = 'product_status';").getSingleResult();
    List<String> enumValuesFromDatabase = new ArrayList<>(Arrays.stream(allEnumValues.split(","))
        .map(s -> s.substring(1, s.length() - 1)).toList());
    List<ProductStatus> productStatusList = new ArrayList<>(Arrays.stream(ProductStatus.values()).toList());
    Iterator<ProductStatus> iterator = productStatusList.iterator();
    while (iterator.hasNext()) {
      String convertedValue = productStatusConverter.convertToDatabaseColumn(iterator.next());
      if (enumValuesFromDatabase.remove(convertedValue)) {
        iterator.remove();
      }
    }

    assertAll(() -> assertEquals(0, enumValuesFromDatabase.size(),
            "Some enum values not mapped properly in code from database. Nothing was mapped for values from database:" + enumValuesFromDatabase),
        () -> assertEquals(0, productStatusList.size(),
            "Some enum values are not in database or can be removed." + productStatusList));
  }
}
