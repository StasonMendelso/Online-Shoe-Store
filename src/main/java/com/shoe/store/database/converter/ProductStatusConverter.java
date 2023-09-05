package com.shoe.store.database.converter;

import com.shoe.store.enums.ProductStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * @author Stanislav Hlova
 */
@Converter(autoApply = true)
public class ProductStatusConverter implements AttributeConverter<ProductStatus,String> {

  @Override
  public String convertToDatabaseColumn(ProductStatus attribute) {
    return attribute.getName();
  }

  @Override
  public ProductStatus convertToEntityAttribute(String dbData) {
    return ProductStatus.getInstance(dbData);
  }
}
