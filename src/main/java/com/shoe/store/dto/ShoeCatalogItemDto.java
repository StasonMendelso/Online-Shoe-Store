package com.shoe.store.dto;

import com.shoe.store.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Stanislav Hlova
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShoeCatalogItemDto {
  private String id;
  private BigDecimal price;
  private String name;
  private ProductStatus productStatus;
}
