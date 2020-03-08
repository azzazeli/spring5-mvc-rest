package com.alexm.spring.spring5mvcrest.service;

import com.alexm.spring.spring5mvcrest.domain.Customer;
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
    private static final String BASE_API_URL = "/api/v1/customers/";

    public CustomerServiceImpl(CustomerRepository repository, CustomerMapper mapper) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public CustomerListDTO allCustomers() {
        return new CustomerListDTO(
                repository.findAll()
                        .stream()
                        .map(this::mapAndSetBaseUrl)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public CustomerDTO customerById(Long id) {
        return repository.findById(id).map( this::mapAndSetBaseUrl).orElseThrow();
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        final Customer customer = mapper.customerDTOToCustomer(customerDTO);
        return saveCustomerAndReturnDto(customer);
    }

    @Override
    public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {
        final Customer customer = mapper.customerDTOToCustomer(customerDTO);
        customer.setId(id);
        return saveCustomerAndReturnDto(customer);
    }

    private CustomerDTO saveCustomerAndReturnDto(Customer customer) {
        final Customer save = repository.save(customer);
        return mapAndSetBaseUrl(save);
    }

    private CustomerDTO mapAndSetBaseUrl(Customer customer) {
        final CustomerDTO customerDTO = mapper.customerToCustomerDTO(customer);
        customerDTO.setCustomerUrl(BASE_API_URL + customer.getId());
        return customerDTO;
    }
}