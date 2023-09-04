package com.shoe.store.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Stanislav Hlova
 */
@AllArgsConstructor
@Getter
public enum ProductStatus {
  IN_AVAILABLE("В наявності"), NOT_IN_AVAILABLE("Немає в наявності");
  private final String name;


  public static ProductStatus getInstance(String name) {
    for (ProductStatus value : ProductStatus.values()) {
      if (value.name.equals(name)) {
        return value;
      }
    }
    throw new IllegalArgumentException("Can't get ProductStatus with name: " + name);
  }
}
