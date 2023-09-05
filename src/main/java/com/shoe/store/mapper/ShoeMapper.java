package com.shoe.store.mapper;

import com.shoe.store.dto.ShoeCatalogItem;
import com.shoe.store.model.shoe.Shoe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Stanislav Hlova
 */
@Mapper(componentModel = "spring")
public interface ShoeMapper {
  
  @Mapping(target = "id", source = "shoe.id", numberFormat = "#")
  ShoeCatalogItem toDto(Shoe shoe);


}
