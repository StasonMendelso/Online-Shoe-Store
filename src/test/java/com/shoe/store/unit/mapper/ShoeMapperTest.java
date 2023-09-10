package com.shoe.store.unit.mapper;

import com.shoe.store.dto.ShoeCardDto;
import com.shoe.store.dto.ShoeCatalogItemDto;
import com.shoe.store.dto.ShoeSizeDto;
import com.shoe.store.enums.ProductStatus;
import com.shoe.store.mapper.ShoeMapper;
import com.shoe.store.model.file.File;
import com.shoe.store.model.shoe.Brand;
import com.shoe.store.model.shoe.Color;
import com.shoe.store.model.shoe.FastenerType;
import com.shoe.store.model.shoe.HeelHeight;
import com.shoe.store.model.shoe.InsoleMaterial;
import com.shoe.store.model.shoe.ManufacturerCountry;
import com.shoe.store.model.shoe.ProductMaterial;
import com.shoe.store.model.shoe.Seasonality;
import com.shoe.store.model.shoe.Sex;
import com.shoe.store.model.shoe.Shoe;
import com.shoe.store.model.shoe.ShoeFile;
import com.shoe.store.model.shoe.ShoeSize;
import com.shoe.store.model.shoe.SockType;
import com.shoe.store.model.shoe.SoleMaterial;
import com.shoe.store.model.shoe.SoleType;
import com.shoe.store.model.shoe.TopMaterial;
import com.shoe.store.unit.BaseUnitTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * @author Stanislav Hlova
 */

class ShoeMapperTest extends BaseUnitTest {

    private final ShoeMapper shoeMapper = Mappers.getMapper(ShoeMapper.class);
    private Shoe shoeInstance;

    @BeforeEach
    void setUp() {
        shoeInstance = Shoe.builder()
                .id(1L)
                .name("name")
                .price(BigDecimal.TEN)
                .productStatus(ProductStatus.IN_AVAILABLE)
                .article("article123-456")
                .brand(Brand.builder()
                        .name("brand1")
                        .build())
                .color(Color.builder()
                        .name("color1")
                        .build())
                .description("description1")
                .fastenerType(FastenerType.builder()
                        .name("fastenerType1")
                        .build())
                .heelHeight(HeelHeight.builder()
                        .name("heelHeight1")
                        .build())
                .insoleMaterial(InsoleMaterial.builder()
                        .name("insoleMaterial1")
                        .build())
                .manufacturerCountry(ManufacturerCountry.builder()
                        .name("manufacture1")
                        .build())
                .sex(Sex.builder()
                        .name("sex1")
                        .build())
                .soleMaterial(SoleMaterial.builder()
                        .name("soleMaterial")
                        .build())
                .productMaterial(ProductMaterial.builder()
                        .name("productMaterial")
                        .build())
                .shoeSizeList(List.of(ShoeSize.builder().size(36).build(), ShoeSize.builder().size(37).build(), ShoeSize.builder().size(38).build()))
                .sockType(SockType.builder()
                        .name("sockType1")
                        .build())
                .soleType(SoleType.builder()
                        .name("soleType1")
                        .build())
                .topMaterial(TopMaterial.builder()
                        .name("topMaterial1")
                        .build())
                .seasonalityList(List.of(Seasonality.builder().name("season1").build(), Seasonality.builder().name("season2").build(), Seasonality.builder().name("season3").build()))
                .model("model1")
                .shoeFileList(List.of(ShoeFile.builder()
                        .file(File.builder()
                                .id(1L).build())
                        .build()))
                .build();
    }

    @Test
    void shouldMapToDto_whenEntityPassed() {
        final ShoeCatalogItemDto expectedDto = ShoeCatalogItemDto.builder()
                .id(String.valueOf(shoeInstance.getId()))
                .name(shoeInstance.getName())
                .price(shoeInstance.getPrice())
                .productStatus(shoeInstance.getProductStatus())
                .build();

        ShoeCatalogItemDto actualDto = shoeMapper.toDto(shoeInstance);

        assertEquals(expectedDto, actualDto);
    }

    @Test
    void shouldMapToCardDto_whenEntityPassed() {
        final ShoeCardDto expectedDto = ShoeCardDto.builder()
                .id(String.valueOf(shoeInstance.getId()))
                .name(shoeInstance.getName())
                .price(shoeInstance.getPrice())
                .productStatus(shoeInstance.getProductStatus())
                .article(shoeInstance.getArticle())
                .brand(shoeInstance.getBrand().getName())
                .color(shoeInstance.getColor().getName())
                .description(shoeInstance.getDescription())
                .fastenerType(shoeInstance.getFastenerType().getName())
                .heelHeight(shoeInstance.getHeelHeight().getName())
                .insoleMaterial(shoeInstance.getInsoleMaterial().getName())
                .manufacturerCountry(shoeInstance.getManufacturerCountry().getName())
                .sex(shoeInstance.getSex().getName())
                .soleMaterial(shoeInstance.getSoleMaterial().getName())
                .productMaterial(shoeInstance.getProductMaterial().getName())
                .shoeSizeList(shoeInstance.getShoeSizeList().stream()
                        .map(shoeSize -> ShoeSizeDto.builder()
                                .size(shoeSize.getSize())
                                .build())
                        .toList())
                .sockType(shoeInstance.getSockType().getName())
                .soleType(shoeInstance.getSoleType().getName())
                .topMaterial(shoeInstance.getTopMaterial().getName())
                .seasonalitiesList(shoeInstance.getSeasonalityList().stream()
                        .map(Seasonality::getName)
                        .toList())
                .model(shoeInstance.getModel())
                .fileIdList(shoeInstance.getShoeFileList().stream()
                        .map(shoeFile -> String.valueOf(shoeFile.getFile().getId()))
                        .toList())
                .build();

        ShoeCardDto actualDto = shoeMapper.toShoeCardDto(shoeInstance);

        assertEquals(expectedDto, actualDto);
    }

    @Test
    void shouldReturnEmptyStringList_whenSeasonalityEmptyListPassed() {
        final int expectedSize = 0;

        final List<String> actual = ShoeMapper.mapSeasonalityList(Collections.emptyList());

        assertEquals(Collections.emptyList(), actual);
        assertEquals(expectedSize, actual.size());
    }

    @Test
    void shouldReturnStringList_whenSeasonalityListPassed() {
        final List<String> expectedList = List.of("Season1", "Season2", "Season3");
        final int expectedSize = expectedList.size();

        final List<String> actual = ShoeMapper.mapSeasonalityList(expectedList.stream()
                .map(value -> Seasonality.builder().name(value).build())
                .toList());

        assertEquals(expectedList, actual);
        assertEquals(expectedSize, actual.size());
    }
}