package com.alexm.spring.spring5mvcrest.service;

import com.alexm.spring.spring5mvcrest.domain.Category;
import com.alexm.spring.spring5mvcrest.domain.CategoryDTO;
import com.alexm.spring.spring5mvcrest.mapper.CategoryMapper;
import com.alexm.spring.spring5mvcrest.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author AlexM
 * Date: 3/3/20
 **/
class CategoryServiceTest {

    private final long ID = 1L;
    private final String FRUITS = "Fruits";
    @Mock
    private CategoryRepository categoryRepository;
    private CategoryService categoryService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);
    }

    @Test
    void getAllCategories() {
        List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());
        when(categoryRepository.findAll()).thenReturn(categories);

        final List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();

        assertEquals(3, categoryDTOS.size());
    }

    @Test
    void getCategoryByName() {
        Category category = new Category();
        category.setId(ID);
        category.setName(FRUITS);

        when(categoryRepository.findByName(FRUITS)).thenReturn(category);

        final CategoryDTO byName = categoryService.getCategoryByName(FRUITS);

        assertEquals(ID, byName.getId());
        assertEquals(FRUITS, byName.getName());
    }
}