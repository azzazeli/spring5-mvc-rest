package com.alexm.spring.spring5mvcrest.service;

import com.alexm.spring.spring5mvcrest.domain.dto.CustomerDTO;
import com.alexm.spring.spring5mvcrest.domain.dto.CustomerListDTO;
import com.alexm.spring.spring5mvcrest.mapper.CustomerMapper;
import com.alexm.spring.spring5mvcrest.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * @author AlexM
 * Date: 3/5/20
 **/
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public CustomerServiceImpl(CustomerRepository repository, CustomerMapper mapper) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public CustomerListDTO allCustomers() {
        return new CustomerListDTO(
                repository.findAll()
                        .stream()
                        .map(customer -> {
                            final CustomerDTO customerDTO = mapper.customerToCustomerDTO(customer);
                            customerDTO.setCustomerUrl("/api/v1/customers/" + customer.getId());
                            return customerDTO;
                        })
                        .collect(Collectors.toList())
        );
    }

    @Override
    public CustomerDTO customerById(Long id) {
        return repository.findById(id).map(mapper::customerToCustomerDTO).orElseThrow();
    }
}
