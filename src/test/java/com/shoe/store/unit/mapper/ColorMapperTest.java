package com.shoe.store.unit.mapper;

import com.shoe.store.dto.ColorDto;
import com.shoe.store.mapper.ColorMapper;
import com.shoe.store.model.shoe.Color;
import com.shoe.store.unit.BaseUnitTest;
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