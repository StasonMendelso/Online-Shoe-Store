package com.shoe.store.controller;

import com.shoe.store.dto.shoe.CreateShoeCardDto;
import com.shoe.store.mapper.ShoeColorStatusMapper;
import com.shoe.store.mapper.ShoeMapper;
import com.shoe.store.model.shoe.Shoe;
import com.shoe.store.resolver.annotation.CreateShoeDto;
import com.shoe.store.service.ShoeService;
import com.shoe.store.validation.validator.PageValidator;
import com.shoe.store.validation.validator.SizePerPageValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;
import java.util.Optional;

/**
 * @author Stanislav Hlova
 */
@Controller
@RequestMapping("/catalog")
@RequiredArgsConstructor
public class CatalogController {

    private final ShoeService shoeService;
    private final PageValidator pageValidator;
    private final ShoeMapper shoeMapper;
    private final ShoeColorStatusMapper shoeColorStatusMapper;
    private final SizePerPageValidator sizePerPageValidator;

    private static final int FIRST_PAGE = 0;

    @GetMapping
    public String viewCatalog(@RequestParam(name = "page", required = false) Integer page,
                              @RequestParam(name = "sizePerPage", required = false, defaultValue = "16") Integer sizePerPage,
                              Model model) {

        if (!pageValidator.isValid(page)) {
            page = 1;
        }
        if (!sizePerPageValidator.isValid(sizePerPage)) {
            sizePerPage = 16;
        }

        Page<Shoe> shoesPerPage = shoeService.getShoesByPage(PageRequest.of(page - 1, sizePerPage));
        if (shoesPerPage.isEmpty()) {
            shoesPerPage = shoeService.getShoesByPage(PageRequest.of(FIRST_PAGE, sizePerPage));
        }

        model.addAllAttributes(Map.of("shoesList", shoesPerPage.get().map(shoeMapper::toDto).toList(),
                "totalShoesNumber", shoesPerPage.getTotalElements(),
                "totalPageNumber", shoesPerPage.getTotalPages(),
                "currentPageNumber", shoesPerPage.getPageable().getPageNumber()));

        return "catalog/catalog";
    }

    @GetMapping("/{id}")
    public String viewProductCard(@PathVariable Long id,
                                  Model model) {
        Optional<Shoe> shoeOptional = shoeService.getShoeById(id);
        return shoeOptional.map(shoe -> {
            model.addAttribute(shoeMapper.toShoeCardDto(shoe));
            model.addAttribute("shoeSiblingColorList", shoeService.getAllShoeColorsById(id).stream()
                    .map(shoeColorStatusMapper::toDto)
                    .toList());
            return "catalog/product_card";
        }).orElse("catalog/product_card_not_found");
    }

    @GetMapping("/add")
    public String getAddPage(CreateShoeCardDto createShoeCardDto, Model model) {

        model.addAttribute("createShoeCardDto", createShoeCardDto);
        model.addAttribute("shoeCharacteristic", shoeService.getAllShoeCharacteristics());
        return "catalog/add_product_card";
    }

    @PostMapping
    public String addNewShoe(@Valid @CreateShoeDto CreateShoeCardDto createShoeCardDto, BindingResult bindingResult,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        //validate by fields
        //custom validator
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("createShoeCardDto", createShoeCardDto);
            return "redirect:/catalog/add";
        }
        Optional<Shoe> optionalShoe = shoeService.addNewShoe(createShoeCardDto);
        if (optionalShoe.isEmpty()) {
            // don't saved for some reasons.
            return "redirect:";
        }
        return "catalog/catalog";
    }
}
