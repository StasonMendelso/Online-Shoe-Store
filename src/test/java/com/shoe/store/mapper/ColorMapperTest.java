package com.shoe.store.mapper;

import com.shoe.store.dto.shoe.ColorDto;
import com.shoe.store.model.shoe.Color;
import com.shoe.store.BaseUnitTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

/**
 * @author Stanislav Hlova
 */
class ColorMapperTest extends BaseUnitTest {
    private final ColorMapper colorMapper = Mappers.getMapper(ColorMapper.class);

    private Color colorInstance;

    @BeforeEach
    void setUp() {
        colorInstance = Color.builder()
                .name("color23")
                .rgb("#123456")
                .id(12L)
                .build();
    }

    @Test
    void shouldReturnDto_whenEntityPassed() {
        final ColorDto expected = ColorDto.builder()
                .name(colorInstance.getName())
                .rgb(colorInstance.getRgb())
                .build();

        final ColorDto actual = colorMapper.toDto(colorInstance);

        assertEquals(expected, actual);
    }

}