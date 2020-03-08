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
    private final String BASE_API_URL = "/api/v1/customers/";

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
                            customerDTO.setCustomerUrl(BASE_API_URL + customer.getId());
                            return customerDTO;
                        })
                        .collect(Collectors.toList())
        );
    }

    @Override
    public CustomerDTO customerById(Long id) {
        return repository.findById(id).map(mapper::customerToCustomerDTO).orElseThrow();
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        final Customer customer = mapper.customerDTOToCustomer(customerDTO);
        final Customer save = repository.save(customer);
        CustomerDTO returnDto = mapper.customerToCustomerDTO(save);
        returnDto.setCustomerUrl(BASE_API_URL + save.getId());
        return returnDto;
    }
}
