package com.alexm.spring.spring5mvcrest.controller.v1;

import com.alexm.spring.spring5mvcrest.domain.dto.CategoryDTO;
import com.alexm.spring.spring5mvcrest.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author AlexM
 * Date: 3/3/20
 **/
class CategoryControllerTest {

    private final String NAME = "Fruits";
    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;

    MockMvc mockMvc;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    void listCategories() throws Exception {
        CategoryDTO category = new CategoryDTO();
        category.setId(1L);
        category.setName(NAME);

        CategoryDTO category2 = new CategoryDTO();
        category.setId(2L);
        category.setName("Bobby");

        List<CategoryDTO> categories = Arrays.asList(category, category2);

        when(categoryService.getAllCategories()).thenReturn(categories);

        mockMvc.perform(get("/api/v1/categories")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories", hasSize(2))
                );
    }

    @Test
    void getCategoryByName() throws Exception {
        CategoryDTO category = new CategoryDTO();
        category.setId(1L);
        category.setName(NAME);

        when(categoryService.getCategoryByName(anyString())).thenReturn(category);

        mockMvc.perform(get("/api/v1/categories/Fruits")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME))
                );
    }
}