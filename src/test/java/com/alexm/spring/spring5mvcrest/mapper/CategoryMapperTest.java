package com.alexm.spring.spring5mvcrest.mapper;

import com.alexm.spring.spring5mvcrest.domain.Category;
import com.alexm.spring.spring5mvcrest.domain.CategoryDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author AlexM
 * Date: 3/2/20
 **/
class CategoryMapperTest {
    CategoryMapper INSTANCE = CategoryMapper.INSTANCE;
    private final long ID = 1L;
    private final String NAME = "Test";

    @Test
    void categoryToCategoryDTO() {
        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);

        final CategoryDTO categoryDTO = INSTANCE.categoryToCategoryDTO(category);
        assertEquals(ID, categoryDTO.getId());
        assertEquals(NAME, categoryDTO.getName());

    }
}