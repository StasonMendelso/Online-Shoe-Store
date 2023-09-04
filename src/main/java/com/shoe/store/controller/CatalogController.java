package com.shoe.store.controller;

import com.shoe.store.model.shoe.Shoe;
import com.shoe.store.service.ShoeService;
import com.shoe.store.validation.validator.PageValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Stanislav Hlova
 */
@Controller
@RequestMapping("/catalog")
@RequiredArgsConstructor
public class CatalogController {

  private final ShoeService shoeService;
  private final PageValidator pageValidator;
  private static final int SIZE_PER_PAGE = 16;
  private static final int FIRST_PAGE = 0;

  @GetMapping
  public String viewCatalog(@RequestParam(name = "page", required = false) Integer page,
      Model model) {
    if (!pageValidator.isValid(page)) {
      page = 1;
    }

    Page<Shoe> shoesPerPage = shoeService.getShoesByPage(PageRequest.of(page - 1, SIZE_PER_PAGE));
    if (shoesPerPage.isEmpty()) {
      shoesPerPage = shoeService.getShoesByPage(PageRequest.of(FIRST_PAGE, SIZE_PER_PAGE));
    }

    model.addAttribute("shoesList", shoesPerPage.get().toList());
    model.addAttribute("totalShoesNumber", shoesPerPage.getTotalElements());
    model.addAttribute("totalPageNumber", shoesPerPage.getTotalPages());
    model.addAttribute("currentPageNumber", shoesPerPage.getPageable().getPageNumber());

    return "catalog/catalog";
  }
}
