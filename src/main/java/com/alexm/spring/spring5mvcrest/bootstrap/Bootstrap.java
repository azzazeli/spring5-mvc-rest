package com.alexm.spring.spring5mvcrest.bootstrap;

import com.alexm.spring.spring5mvcrest.domain.Category;
import com.alexm.spring.spring5mvcrest.domain.Customer;
import com.alexm.spring.spring5mvcrest.repositories.CategoryRepository;
import com.alexm.spring.spring5mvcrest.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author AlexM
 * Date: 2/27/20
 **/
@Component
public class Bootstrap implements CommandLineRunner {
    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadCustomers();
    }

    private void loadCustomers() {
        Customer dybala = new Customer();
        dybala.setFirstName("Paulo");
        dybala.setLastName("Dybala");
        Customer ronaldo = new Customer();
        ronaldo.setFirstName("Cristiano");
        ronaldo.setLastName("Ronaldo");
        customerRepository.save(dybala);
        customerRepository.save(ronaldo);

        System.out.println("Customers loaded = " + customerRepository.count());
   }

    private void loadCategories() {
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


        System.out.println("Categories loaded = " + categoryRepository.count());
    }
}
