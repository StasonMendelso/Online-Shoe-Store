package com.shoe.store.dto.shoe;

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
public class CreateShoeCardDto {
    private String name;
    private String model;
    private String article;
    private BigDecimal price;
    private String description;
    private String sex;
    private String brand;
    private String manufacturerCountry;
    private String soleMaterial;
    private String topMaterial;
    private String insoleMaterial;
    private String productMaterial;
    private String soleType;
    private String heelHeight;
    private String fastenerType;
    private String sockType;
    private String category;
    private String color;
    private List<String> seasonalitiesList;
    private List<ShoeSizeDto> shoeSizeList;
}
