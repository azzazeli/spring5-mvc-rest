package com.alexm.spring.spring5mvcrest.repositories;

import com.alexm.spring.spring5mvcrest.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author AlexM
 * Date: 2/27/20
 **/
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
