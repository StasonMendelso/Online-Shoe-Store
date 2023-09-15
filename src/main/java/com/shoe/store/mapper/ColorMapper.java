package com.shoe.store.mapper;

import com.shoe.store.dto.shoe.ColorDto;
import com.shoe.store.model.shoe.Color;
import org.mapstruct.Mapper;

/**
 * @author Stanislav Hlova
 */
@Mapper(componentModel = "spring")
public interface ColorMapper {

    ColorDto toDto(Color color);
}
