package com.shoe.store.mapper;

import com.shoe.store.dto.ColorDto;
import com.shoe.store.dto.ShoeColorStatusDto;
import com.shoe.store.enums.ProductStatus;
import com.shoe.store.model.shoe.Color;
import com.shoe.store.model.shoe.Shoe;
import com.shoe.store.BaseUnitTest;
import lombok.RequiredArgsConstructor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author Stanislav Hlova
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        ShoeColorStatusMapperImpl.class,
        ColorMapperImpl.class,
})
@RequiredArgsConstructor
class ShoeColorStatusMapperTest extends BaseUnitTest {
    private final ShoeColorStatusMapper shoeColorStatusMapper;

    private Shoe shoeInstance;

    @BeforeEach
    void setUp() {
        shoeInstance = Shoe.builder()
                .color(Color.builder()
                        .name("color")
                        .rgb("#123456")
                        .build())
                .productStatus(ProductStatus.NOT_IN_AVAILABLE)
                .id(12L)
                .build();
    }

    @Test
    void shouldReturnDto_whenEntityPassed() {
        final ShoeColorStatusDto expected = ShoeColorStatusDto.builder()
                .id(String.valueOf(shoeInstance.getId()))
                .colorDto(ColorDto.builder()
                        .rgb(shoeInstance.getColor().getRgb())
                        .name(shoeInstance.getColor().getName())
                        .build())
                .productStatus(shoeInstance.getProductStatus())
                .build();

        final ShoeColorStatusDto actual = shoeColorStatusMapper.toDto(shoeInstance);

        assertEquals(expected, actual);
    }

}