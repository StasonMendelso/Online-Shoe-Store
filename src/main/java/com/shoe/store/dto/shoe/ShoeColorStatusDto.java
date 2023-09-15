package com.shoe.store.dto.shoe;

import com.shoe.store.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Stanislav Hlova
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShoeColorStatusDto {
    private String id;
    private ColorDto colorDto;
    private ProductStatus productStatus;
}
