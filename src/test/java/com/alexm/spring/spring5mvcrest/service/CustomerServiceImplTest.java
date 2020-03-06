package com.alexm.spring.spring5mvcrest.service;

import com.alexm.spring.spring5mvcrest.domain.Customer;
import com.alexm.spring.spring5mvcrest.domain.dto.CustomerDTO;
import com.alexm.spring.spring5mvcrest.domain.dto.CustomerListDTO;
import com.alexm.spring.spring5mvcrest.mapper.CustomerMapper;
import com.alexm.spring.spring5mvcrest.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author AlexM
 * Date: 3/5/20
 **/
class CustomerServiceImplTest {

    private static final Long ID = 1L;
    @Mock
    CustomerRepository repository;
    CustomerServiceImpl customerService;
    CustomerMapper mapper = CustomerMapper.INSTANCE;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(repository, mapper);
        List<Customer> fakeCustomers = new ArrayList<>();
        final Customer customer1 = new Customer();
        customer1.setId(1L);
        fakeCustomers.add(customer1);
        fakeCustomers.add(new Customer());
        when(repository.findAll()).thenReturn(fakeCustomers);
    }

    @Test
    void allCustomers() {
        final CustomerListDTO customerListDTO = customerService.allCustomers();
        assertEquals(2, customerListDTO.getCustomers().size());
        assertEquals("/api/v1/customers/1", customerListDTO.getCustomers().get(0).getCustomerUrl());
    }

    @Test
    void customerById() {
        Customer customer = new Customer();
        customer.setId(ID);

        when(repository.findById(ID)).thenReturn(Optional.of(customer));
        final CustomerDTO byId = customerService.customerById(ID);
        assertEquals(ID, byId.getId());
    }
}