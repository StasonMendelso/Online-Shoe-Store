package com.shoe.store.dto.shoe;

import com.shoe.store.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Stanislav Hlova
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShoeCardDto {
    private String id;
    private BigDecimal price;
    private String name;
    private ProductStatus productStatus;
    private String article;
    private String model;
    private String brand;
    private String manufacturerCountry;
    private List<String> seasonalitiesList;
    private String color;
    private String sex;
    private String soleMaterial;
    private String topMaterial;
    private String insoleMaterial;
    private String productMaterial;
    private String soleType;
    private String heelHeight;
    private String fastenerType;
    private String sockType;
    private String description;
    private List<ShoeSizeDto> shoeSizeList;
    private List<String> fileIdList;

}
