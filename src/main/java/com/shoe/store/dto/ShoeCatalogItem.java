package com.shoe.store.dto;

import com.shoe.store.enums.ProductStatus;
import java.math.BigDecimal;

/**
 * @author Stanislav Hlova
 */
public class ShoeCatalogItem {
  private Long id;
  private BigDecimal price;
  private String name;
  private ProductStatus productStatus;
}
