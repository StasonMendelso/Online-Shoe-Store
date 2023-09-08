package com.shoe.store.dto;

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
public class ColorDto {
    private String name;
    private String rgb;
}
