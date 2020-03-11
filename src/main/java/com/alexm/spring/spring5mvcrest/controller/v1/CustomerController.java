package com.alexm.spring.spring5mvcrest.controller.v1;

import com.alexm.spring.spring5mvcrest.domain.dto.CustomerDTO;
import com.alexm.spring.spring5mvcrest.domain.dto.CustomerListDTO;
import com.alexm.spring.spring5mvcrest.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author AlexM
 * Date: 3/5/20
 **/
@RestController
@RequestMapping(CustomerController.API_URL_V1)
public class CustomerController {
    public static final String API_URL_V1 = "/api/v1/customers/";

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomerListDTO allCustomers() {
        return customerService.allCustomers();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{id}")
    public CustomerDTO customerById(@PathVariable Long id) {
        return customerService.customerById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CustomerDTO createNewCustomer(@RequestBody CustomerDTO dto) {
        return customerService.createNewCustomer(dto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO dto) {
        return customerService.saveCustomerByDTO(id, dto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public CustomerDTO patchCustomer(@PathVariable Long id, @RequestBody CustomerDTO dto) {
        return customerService.patchCustomer(id, dto);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
}
