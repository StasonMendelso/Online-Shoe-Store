package com.shoe.store.unit.controller;

import com.shoe.store.controller.CatalogController;
import com.shoe.store.dto.ShoeCatalogItem;
import com.shoe.store.mapper.ShoeMapper;
import com.shoe.store.model.shoe.Shoe;
import com.shoe.store.service.ShoeService;
import com.shoe.store.unit.BaseUnitTest;
import com.shoe.store.validation.validator.PageValidator;
import com.shoe.store.validation.validator.SizePerPageValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

/**
 * @author Stanislav Hlova
 */

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
    private List<Shoe> shoeList;

    @BeforeEach
    void setUp() {
        shoeList = List.of(new Shoe(), new Shoe(), new Shoe());
        when(shoePage.getTotalElements()).thenReturn(10L);
        when(shoePage.getTotalPages()).thenReturn(5);
        when(shoePage.getPageable()).thenReturn(shoePageable);
        when(shoePage.get()).thenReturn(shoeList.stream());
        when(shoePageable.getPageNumber()).thenReturn(0);
        when(shoeMapper.toDto(any(Shoe.class))).thenReturn(new ShoeCatalogItem());
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
}