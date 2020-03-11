package com.alexm.spring.spring5mvcrest.controller.v1;

import com.alexm.spring.spring5mvcrest.domain.dto.CustomerDTO;
import com.alexm.spring.spring5mvcrest.domain.dto.CustomerListDTO;
import com.alexm.spring.spring5mvcrest.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author AlexM
 * Date: 3/5/20
 **/
@Controller
@RequestMapping(CustomerController.API_URL_V1)
public class CustomerController {
    public static final String API_URL_V1 = "/api/v1/customers/";

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<CustomerListDTO> allCustomers() {
        return new ResponseEntity<>(customerService.allCustomers(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CustomerDTO> customerById(@PathVariable Long id) {
        return new ResponseEntity<>(customerService.customerById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO dto) {
        return new ResponseEntity<>(customerService.createNewCustomer(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO dto) {
        return new ResponseEntity<>(customerService.saveCustomerByDTO(id, dto), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerDTO> patchCustomer(@PathVariable Long id, @RequestBody CustomerDTO dto) {
        return new ResponseEntity<>(customerService.patchCustomer(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
