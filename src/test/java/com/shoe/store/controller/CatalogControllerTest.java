package com.shoe.store.controller;

import com.shoe.store.BaseUnitTest;
import com.shoe.store.dto.ShoeCardDto;
import com.shoe.store.dto.ShoeCatalogItemDto;
import com.shoe.store.dto.ShoeColorStatusDto;
import com.shoe.store.mapper.ShoeColorStatusMapper;
import com.shoe.store.mapper.ShoeMapper;
import com.shoe.store.model.shoe.Shoe;
import com.shoe.store.service.ShoeService;
import com.shoe.store.validation.validator.PageValidator;
import com.shoe.store.validation.validator.SizePerPageValidator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Stanislav Hlova
 */

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CatalogControllerTest extends BaseUnitTest {

    @InjectMocks
    private CatalogController catalogController;

    @Mock
    private ShoeService shoeService;
    @Mock
    private PageValidator pageValidator;
    @Mock
    private ShoeMapper shoeMapper;
    @Mock
    private SizePerPageValidator sizePerPageValidator;
    @Mock
    private Model model;
    @Mock
    private PageRequest pageRequest;
    @Mock
    private Page<Shoe> shoePage;
    @Mock
    private Pageable shoePageable;
    @Mock
    private ShoeColorStatusMapper shoeColorStatusMapper;

    private List<Shoe> shoeList;
    @BeforeEach
    void setUp() {
        shoeList = List.of(new Shoe(), new Shoe(), new Shoe());
        when(shoePage.getTotalElements()).thenReturn(10L);
        when(shoePage.getTotalPages()).thenReturn(5);
        when(shoePage.getPageable()).thenReturn(shoePageable);
        when(shoePage.get()).thenReturn(shoeList.stream());
        when(shoePageable.getPageNumber()).thenReturn(0);
        when(shoeMapper.toDto(any(Shoe.class))).thenReturn(new ShoeCatalogItemDto());
    }

    @Test
    void shouldAddAttributesToModel_whenViewCatalog() {
        final int sizePerPage = 10;
        final int page = 1;
        try (MockedStatic<PageRequest> pageRequestStaticMocked = Mockito.mockStatic(PageRequest.class)) {
            when(pageValidator.isValid(page)).thenReturn(Boolean.TRUE);
            when(sizePerPageValidator.isValid(sizePerPage)).thenReturn(Boolean.TRUE);
            pageRequestStaticMocked.when(() -> PageRequest.of(page - 1, sizePerPage)).thenReturn(pageRequest);
            when(shoeService.getShoesByPage(pageRequest)).thenReturn(shoePage);
            when(shoePage.isEmpty()).thenReturn(Boolean.FALSE);

            catalogController.viewCatalog(page, sizePerPage, model);

            verify(model, times(1)).addAllAttributes(
                    Map.of("shoesList", shoeList.stream().map(shoeMapper::toDto).toList(),
                            "totalShoesNumber", shoePage.getTotalElements(),
                            "totalPageNumber", shoePage.getTotalPages(),
                            "currentPageNumber", shoePageable.getPageNumber()));
        }

    }

    @Test
    void shouldSetDefaultSizePerPageAndPage_whenParameterInvalid() {
        final int sizePerPage = -10;
        final int page = -1;
        final int defaultPage = 1;
        final int defaultSizePerPage = 16;
        try (MockedStatic<PageRequest> pageRequestStaticMocked = Mockito.mockStatic(PageRequest.class)) {
            when(pageValidator.isValid(page)).thenReturn(Boolean.FALSE);
            when(sizePerPageValidator.isValid(sizePerPage)).thenReturn(Boolean.FALSE);
            pageRequestStaticMocked.when(() -> PageRequest.of(defaultPage - 1, defaultSizePerPage))
                    .thenReturn(pageRequest);
            when(shoeService.getShoesByPage(pageRequest)).thenReturn(shoePage);
            when(shoePage.isEmpty()).thenReturn(Boolean.FALSE);

            catalogController.viewCatalog(page, sizePerPage, model);

            pageRequestStaticMocked.verify(() -> PageRequest.of(defaultPage - 1, defaultSizePerPage),
                    times(1));
        }
    }

    @Test
    void shouldReturnFirstPage_whenPageIsEmpty() {
        final int sizePerPage = 10;
        final int page = 3;
        final int firstPage = 0;
        final PageRequest pageRequestWithFirstPage = mock(PageRequest.class);
        try (MockedStatic<PageRequest> pageRequestStaticMocked = Mockito.mockStatic(PageRequest.class);) {
            when(pageValidator.isValid(page)).thenReturn(Boolean.TRUE);
            when(sizePerPageValidator.isValid(sizePerPage)).thenReturn(Boolean.TRUE);
            pageRequestStaticMocked.when(() -> PageRequest.of(page - 1, sizePerPage))
                    .thenReturn(pageRequest);
            pageRequestStaticMocked.when(() -> PageRequest.of(firstPage, sizePerPage))
                    .thenReturn(pageRequestWithFirstPage);
            when(shoeService.getShoesByPage(pageRequest)).thenReturn(shoePage);
            when(shoeService.getShoesByPage(pageRequestWithFirstPage)).thenReturn(shoePage);
            when(shoePage.isEmpty()).thenReturn(Boolean.TRUE);

            catalogController.viewCatalog(page, sizePerPage, model);

            pageRequestStaticMocked.verify(() -> PageRequest.of(firstPage, sizePerPage), times(1));
        }

    }

    @Test
    void shouldAddAttributesToModel_whenViewShoeCard() {
        final Long id = 4L;
        final Optional<Shoe> shoeOptional = Optional.of(new Shoe());
        final ShoeCardDto shoeCardDto = new ShoeCardDto();
        when(shoeService.getShoeById(id)).thenReturn(shoeOptional);
        when(shoeService.getAllShoeColorsById(id)).thenReturn(shoeList);
        when(shoeMapper.toShoeCardDto(shoeOptional.get())).thenReturn(shoeCardDto);
        when(shoeColorStatusMapper.toDto(shoeOptional.get())).thenReturn(new ShoeColorStatusDto());

        String viewName = catalogController.viewProductCard(id, model);

        assertEquals("catalog/product_card", viewName);
        verify(model, times(1)).addAttribute(shoeCardDto);
        verify(model, times(1)).addAttribute("shoeSiblingColorList", shoeList.stream()
                .map(shoeColorStatusMapper::toDto)
                .toList());

    }
    @Test
    void shouldReturnNotFoundPage_whenNoProductFind() {
        final Long id = 4L;
        final Optional<Shoe> shoeOptional = Optional.empty();
        when(shoeService.getShoeById(id)).thenReturn(shoeOptional);

        String viewName = catalogController.viewProductCard(id, model);

        assertEquals("catalog/product_card_not_found", viewName);
        verifyNoInteractions(model);
    }
}