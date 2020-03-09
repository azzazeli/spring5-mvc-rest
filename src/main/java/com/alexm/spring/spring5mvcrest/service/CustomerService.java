package com.alexm.spring.spring5mvcrest.service;

import com.alexm.spring.spring5mvcrest.domain.dto.CustomerDTO;
import com.alexm.spring.spring5mvcrest.domain.dto.CustomerListDTO;

/**
 * @author AlexM
 * Date: 3/5/20
 **/
public interface CustomerService {
    CustomerListDTO allCustomers();
    CustomerDTO customerById(Long id);
    CustomerDTO createNewCustomer(CustomerDTO customerDTO);
    CustomerDTO saveCustomerByDTO(Long id,  CustomerDTO customerDto);
    CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO);
}
