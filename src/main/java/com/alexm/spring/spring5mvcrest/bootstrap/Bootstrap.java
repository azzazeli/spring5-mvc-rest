package com.alexm.spring.spring5mvcrest.bootstrap;

import com.alexm.spring.spring5mvcrest.domain.Category;
import com.alexm.spring.spring5mvcrest.repositories.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author AlexM
 * Date: 2/27/20
 **/
@Component
public class Bootstrap implements CommandLineRunner {
    private CategoryRepository categoryRepository;

    public Bootstrap(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Category fruits = new Category();
        fruits.setName("Fruits");
        Category dried = new Category();
        dried.setName("Dried");
        Category exotic = new Category();
        exotic.setName("Exotic");
        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);


        System.out.println("Data loaded = " + categoryRepository.count());

    }
}
