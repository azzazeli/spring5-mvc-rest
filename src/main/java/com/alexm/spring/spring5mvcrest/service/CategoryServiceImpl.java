package com.alexm.spring.spring5mvcrest.service;

import com.alexm.spring.spring5mvcrest.domain.dto.CategoryDTO;
import com.alexm.spring.spring5mvcrest.mapper.CategoryMapper;
import com.alexm.spring.spring5mvcrest.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author AlexM
 * Date: 3/3/20
 **/
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper mapper;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryMapper mapper, CategoryRepository categoryRepository) {
        this.mapper = mapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(mapper::categoryToCategoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        return mapper.categoryToCategoryDTO(categoryRepository.findByName(name));
    }
}
