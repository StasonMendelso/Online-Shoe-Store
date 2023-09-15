package com.shoe.store.service;

import com.shoe.store.database.repository.ShoeRepository;
import com.shoe.store.dto.shoe.CreateShoeCardDto;
import com.shoe.store.dto.shoe.ShoeCharacteristicDto;
import com.shoe.store.dto.shoe.ShoeCharacteristicDto.ShoeCharacteristicDtoBuilder;
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
import com.shoe.store.model.shoe.ShoeCategory;
import com.shoe.store.model.shoe.SockType;
import com.shoe.store.model.shoe.SoleMaterial;
import com.shoe.store.model.shoe.SoleType;
import com.shoe.store.model.shoe.TopMaterial;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Stanislav Hlova
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ShoeService {

    private final ShoeRepository shoeRepository;

    public Page<Shoe> getShoesByPage(Pageable pageable) {
        return shoeRepository.findAllBy(pageable);
    }

    public Optional<Shoe> getShoeById(Long id) {
        return shoeRepository.findById(id);
    }

    public List<Shoe> getAllShoeColorsById(Long id) {
        return shoeRepository.findAllShoeColorsById(id);
    }

    public ShoeCharacteristicDto getAllShoeCharacteristics() {
        ShoeCharacteristicDtoBuilder builder = ShoeCharacteristicDto.builder();

        builder.sexList(shoeRepository.findAllSex().stream().map(Sex::getName).toList())
                .brandList(shoeRepository.findAllBrand().stream().map(Brand::getName).toList())
                .manufacturerCountryList(shoeRepository.findAllManufacturerCountry().stream().map(ManufacturerCountry::getName).toList())
                .soleMaterialList(shoeRepository.findAllSoleMaterial().stream().map(SoleMaterial::getName).toList())
                .categoryList(shoeRepository.findAllCategory().stream().map(ShoeCategory::getName).toList())
                .colorList(shoeRepository.findAllColor().stream().map(Color::getName).toList())
                .fastenerTypeList(shoeRepository.findAllFastenerType().stream().map(FastenerType::getName).toList())
                .heelHeightList(shoeRepository.findAllHeelHeight().stream().map(HeelHeight::getName).toList())
                .insoleMaterialList(shoeRepository.findAllInsoleMaterial().stream().map(InsoleMaterial::getName).toList())
                .productMaterialList(shoeRepository.findAllProductMaterial().stream().map(ProductMaterial::getName).toList())
                .seasonalityList(shoeRepository.findAllSeasonality().stream().map(Seasonality::getName).toList())
                .sockTypeList(shoeRepository.findAllSockType().stream().map(SockType::getName).toList())
                .topMaterialList(shoeRepository.findAllTopMaterial().stream().map(TopMaterial::getName).toList())
                .soleTypeList(shoeRepository.findAllSoleType().stream().map(SoleType::getName).toList());

        return builder.build();
    }

    public Optional<Shoe> addNewShoe(CreateShoeCardDto createShoeCardDto) {
//        Shoe saved = shoeRepository.save();
        return Optional.empty();
    }
}
