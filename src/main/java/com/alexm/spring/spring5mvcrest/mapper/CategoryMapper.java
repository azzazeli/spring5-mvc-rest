package com.alexm.spring.spring5mvcrest.mapper;

import com.alexm.spring.spring5mvcrest.domain.Category;
import com.alexm.spring.spring5mvcrest.domain.dto.CategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author AlexM
 * Date: 3/2/20
 **/
@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);

}
