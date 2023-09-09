package com.shoe.store.mapper;

import com.shoe.store.dto.ShoeCardDto;
import com.shoe.store.dto.ShoeCatalogItemDto;
import com.shoe.store.model.shoe.Seasonality;
import com.shoe.store.model.shoe.Shoe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

/**
 * @author Stanislav Hlova
 */
@Mapper(componentModel = "spring")
public interface ShoeMapper {

    @Mapping(target = "id", source = "shoe.id", numberFormat = "#")
    ShoeCatalogItemDto toDto(Shoe shoe);

    @Mapping(target = "brand", source = "shoe.brand.name")
    @Mapping(target = "manufacturerCountry", source = "shoe.manufacturerCountry.name")
    @Mapping(target = "sex", source = "shoe.sex.name")
    @Mapping(target = "soleMaterial", source = "shoe.soleMaterial.name")
    @Mapping(target = "insoleMaterial", source = "shoe.insoleMaterial.name")
    @Mapping(target = "topMaterial", source = "shoe.topMaterial.name")
    @Mapping(target = "productMaterial", source = "shoe.productMaterial.name")
    @Mapping(target = "soleType", source = "shoe.soleType.name")
    @Mapping(target = "heelHeight", source = "shoe.heelHeight.name")
    @Mapping(target = "fastenerType", source = "shoe.fastenerType.name")
    @Mapping(target = "sockType", source = "shoe.sockType.name")
    @Mapping(target = "color", source = "shoe.color.name")
    @Mapping(target = "seasonalitiesList", source = "shoe.seasonalityList", qualifiedByName = "seasonalityListToStringList")
    @Mapping(target = "shoeSizeList", source = "shoe.shoeSizeList")
    ShoeCardDto toShoeCardDto(Shoe shoe);

    @Named("seasonalityListToStringList")
    static List<String> map(List<Seasonality> seasonalityList) {
        return seasonalityList.stream().map(Seasonality::getName).toList();
    }
}
