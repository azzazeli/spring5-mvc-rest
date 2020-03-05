package com.alexm.spring.spring5mvcrest.service;

import com.alexm.spring.spring5mvcrest.domain.dto.CategoryDTO;

import java.util.List;

/**
 * @author AlexM
 * Date: 3/3/20
 **/
public interface CategoryService {
    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryByName(String name);
}
