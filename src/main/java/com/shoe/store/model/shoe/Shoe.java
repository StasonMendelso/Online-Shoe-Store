package com.shoe.store.model.shoe;

import com.shoe.store.database.converter.ProductStatusConverter;
import com.shoe.store.enums.ProductStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Stanislav Hlova
 */
@Entity
@Table(name = "shoes")
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Shoe {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;
  @Column(name = "price")
  private BigDecimal price;

  @Column(name = "product_status")
  @Convert(converter = ProductStatusConverter.class)
  private ProductStatus productStatus;
}
