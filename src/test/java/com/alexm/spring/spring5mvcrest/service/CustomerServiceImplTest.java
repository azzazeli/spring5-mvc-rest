package com.alexm.spring.spring5mvcrest.service;

import com.alexm.spring.spring5mvcrest.domain.Customer;
import com.alexm.spring.spring5mvcrest.domain.dto.CustomerDTO;
import com.alexm.spring.spring5mvcrest.domain.dto.CustomerListDTO;
import com.alexm.spring.spring5mvcrest.mapper.CustomerMapper;
import com.alexm.spring.spring5mvcrest.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
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
        assertEquals("/api/v1/customers/1", byId.getCustomerUrl());
    }

    @Test
    void newCustomer() {
        final long id = 332L;
        final String paulo = "Paulo";
        final String dibala = "Dibala";

        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);
        final Customer customer = new Customer();
        customer.setId(id);
        when(repository.save(any(Customer.class))).thenReturn(customer);
        final CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname(paulo);
        customerDTO.setLastname(dibala);

        final CustomerDTO newCustomer = customerService.createNewCustomer(customerDTO);

        verify(repository).save(customerArgumentCaptor.capture());
        assertEquals(paulo, customerArgumentCaptor.getValue().getFirstName());
        assertEquals(dibala, customerArgumentCaptor.getValue().getLastName());
        assertEquals(id, newCustomer.getId());
        assertEquals("/api/v1/customers/" + id, newCustomer.getCustomerUrl());
    }

    @Test
    void saveCustomerByDto() {
        Long id = 22L;
        final String goodFirst = "GoodFirst";
        final String goodLast = "GoodLast";

        CustomerDTO dto = new CustomerDTO();
        dto.setFirstname(goodFirst);
        dto.setLastname(goodLast);

        Customer customer = new Customer();
        customer.setLastName(dto.getLastname());
        customer.setFirstName(dto.getFirstname());
        customer.setId(id);
        when(repository.save(any(Customer.class))).thenReturn(customer);
        final CustomerDTO savedDto = customerService.saveCustomerByDTO(id, dto);

        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);
        verify(repository).save(customerArgumentCaptor.capture());
        assertEquals(goodFirst, customerArgumentCaptor.getValue().getFirstName());
        assertEquals(goodLast, customerArgumentCaptor.getValue().getLastName());
        assertEquals(id, customerArgumentCaptor.getValue().getId());

        assertEquals(goodFirst, savedDto.getFirstname());
        assertEquals(goodLast, savedDto.getLastname());
        assertEquals("/api/v1/customers/" + id, savedDto.getCustomerUrl());
    }
}