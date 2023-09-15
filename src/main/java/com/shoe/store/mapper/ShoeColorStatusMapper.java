package com.shoe.store.mapper;

import com.shoe.store.dto.shoe.ShoeColorStatusDto;
import com.shoe.store.model.shoe.Shoe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Stanislav Hlova
 */
@Mapper(componentModel = "spring", uses = ColorMapper.class)
public interface ShoeColorStatusMapper {
    @Mapping(target = "id", source = "shoe.id", numberFormat = "#")
    @Mapping(target = "colorDto", source = "shoe.color")
    ShoeColorStatusDto toDto(Shoe shoe);
}
