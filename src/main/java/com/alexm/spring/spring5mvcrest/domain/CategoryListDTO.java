package com.alexm.spring.spring5mvcrest.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author AlexM
 * Date: 3/4/20
 **/
@Data
@AllArgsConstructor
public class CategoryListDTO {
    List<CategoryDTO> categories;
}
