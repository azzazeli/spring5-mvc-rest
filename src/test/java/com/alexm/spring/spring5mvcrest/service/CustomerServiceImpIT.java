package com.alexm.spring.spring5mvcrest.service;

import com.alexm.spring.spring5mvcrest.bootstrap.Bootstrap;
import com.alexm.spring.spring5mvcrest.domain.Customer;
import com.alexm.spring.spring5mvcrest.domain.dto.CustomerDTO;
import com.alexm.spring.spring5mvcrest.mapper.CustomerMapper;
import com.alexm.spring.spring5mvcrest.repositories.CategoryRepository;
import com.alexm.spring.spring5mvcrest.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author AlexM
 * Date: 3/9/20
 **/
@DataJpaTest
public class CustomerServiceImpIT {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CategoryRepository categoryRepository;

    CustomerService customerService;

    @BeforeEach
    void setup() throws Exception {
        Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository);
        bootstrap.run();
        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    void patchCustomerUpdateFirstName() {
        final String roberto = "Roberto";
        CustomerDTO patchDto = new CustomerDTO();
        patchDto.setFirstname(roberto);

        final Long id = customerIdValue();
        final Customer originalCustomer = customerById(id);
        final String originalFirstName = originalCustomer.getFirstName();
        final String originalLastName = originalCustomer.getLastName();

        final CustomerDTO patchCustomer = customerService.patchCustomer(id, patchDto);

        assertNotNull(patchCustomer);
        assertEquals(id, patchCustomer.getId());
        assertEquals(roberto, patchCustomer.getFirstname());
        assertEquals(originalLastName, patchCustomer.getLastname());
        assertNotEquals(originalFirstName, patchCustomer.getFirstname());
    }

    @Test
    void patchCustomerUpdateLastName() {
        final String badjo = "Badjo";
        CustomerDTO patchDto = new CustomerDTO();
        patchDto.setLastname(badjo);

        final Long id = customerIdValue();
        final Customer originalCustomer = customerById(id);
        final String originalFirstName = originalCustomer.getFirstName();
        final String originalLastName = originalCustomer.getLastName();

        final CustomerDTO patchCustomer = customerService.patchCustomer(id, patchDto);

        assertNotNull(patchCustomer);
        assertEquals(id, patchCustomer.getId());
        assertEquals(badjo, patchCustomer.getLastname());
        assertEquals(originalFirstName, patchCustomer.getFirstname());
        assertNotEquals(originalLastName, patchCustomer.getLastname());
    }

    private Customer customerById(Long id) {
        return customerRepository.getOne(id);
    }

    private Long customerIdValue() {
        return customerRepository.findAll().get(0).getId();
    }
}
