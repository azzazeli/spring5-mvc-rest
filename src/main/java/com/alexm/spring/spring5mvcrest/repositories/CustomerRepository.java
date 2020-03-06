package com.alexm.spring.spring5mvcrest.repositories;

import com.alexm.spring.spring5mvcrest.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author AlexM
 * Date: 3/5/20
 **/
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
