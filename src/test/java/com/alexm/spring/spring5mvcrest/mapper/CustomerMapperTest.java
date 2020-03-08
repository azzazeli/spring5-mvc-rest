package com.alexm.spring.spring5mvcrest.mapper;

import com.alexm.spring.spring5mvcrest.domain.Customer;
import com.alexm.spring.spring5mvcrest.domain.dto.CustomerDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author AlexM
 * Date: 3/5/20
 **/
class CustomerMapperTest {
    CustomerMapper mapper = CustomerMapper.INSTANCE;
    private Long ID = 1L;
    private String FIRST_NAME = "Paulo";
    private String LAST_NAME = "Dybala";

    @Test
    void customerToCustomerDTO() {
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);

        final CustomerDTO customerDTO = mapper.customerToCustomerDTO(customer);
        assertAll("customerDto",
                () -> assertEquals(ID, customerDTO.getId()),
                () -> assertEquals(FIRST_NAME, customerDTO.getFirstname()),
                () -> assertEquals(LAST_NAME, customerDTO.getLastname()));
    }
}