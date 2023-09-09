package com.shoe.store.integration.controller;

import com.shoe.store.integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * @author Stanislav Hlova
 */
@RequiredArgsConstructor
class CatalogControllerIT extends IntegrationTestBase {

    private final MockMvc mockMvc;

    @Test
    void shouldReturnOk_whenViewCatalog() throws Exception {

        mockMvc.perform(get("/catalog")
                        .param("page", "2")
                        .param("sizePerPage", "10"))
                .andExpect(status().isOk())
                .andExpect(view().name("catalog/catalog"))
                .andExpect(model().attribute("shoesList", hasSize(10)))
                .andExpect(model().attribute("totalShoesNumber", 20L))
                .andExpect(model().attribute("totalPageNumber", 2))
                .andExpect(model().attribute("currentPageNumber", 1))
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.cookie().doesNotExist("time"))
                .andExpect(MockMvcResultMatchers.header().doesNotExist("sdf"));
    }
    @Test
    void shouldReturnProductCardNotFound_whenProductNotFound() throws Exception {
        mockMvc.perform(get("/catalog/100000"))
                .andExpect(status().isOk())
                .andExpect(view().name("catalog/product_card_not_found"))
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(model().hasNoErrors());

    }
    @Test
    void shouldReturnProductCard_whenProductFound() throws Exception {
        mockMvc.perform(get("/catalog/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("catalog/product_card"))
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeExists("shoeSiblingColorList","shoeCardDto"));

    }
}