package com.shoe.store.dto.shoe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Stanislav Hlova
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShoeCharacteristicDto {
    private List<String> sexList;
    private List<String> brandList;
    private List<String> manufacturerCountryList;
    private List<String> soleMaterialList;
    private List<String> topMaterialList;
    private List<String> insoleMaterialList;
    private List<String> productMaterialList;
    private List<String> soleTypeList;
    private List<String> heelHeightList;
    private List<String> fastenerTypeList;
    private List<String> sockTypeList;
    private List<String> categoryList;
    private List<String> colorList;
    private List<String> seasonalityList;
}
