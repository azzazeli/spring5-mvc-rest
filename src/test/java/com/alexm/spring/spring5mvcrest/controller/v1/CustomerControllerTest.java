package com.alexm.spring.spring5mvcrest.controller.v1;

import com.alexm.spring.spring5mvcrest.domain.dto.CustomerDTO;
import com.alexm.spring.spring5mvcrest.domain.dto.CustomerListDTO;
import com.alexm.spring.spring5mvcrest.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static com.alexm.spring.spring5mvcrest.controller.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author AlexM
 * Date: 3/5/20
 **/
class CustomerControllerTest {
    @Mock
    CustomerService customerService;
    @InjectMocks
    CustomerController controller;

    MockMvc mvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void allCustomers() throws Exception {
        when(customerService.allCustomers()).thenReturn(new CustomerListDTO(Arrays.asList(new CustomerDTO())));
        mvc.perform(get("/api/v1/customers").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.customers", hasSize(1)));
    }

    @Test
    void customerById() throws Exception {
        CustomerDTO dto = new CustomerDTO();
        final Long id = 1L;
        dto.setId(id);

        when(customerService.customerById(id)).thenReturn(dto);

        mvc.perform(get("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.id", equalTo(id.intValue()))
                );
    }

    @Test
    void createNewCustomer() throws Exception {
        CustomerDTO customerDto =new CustomerDTO();
        final String duglas = "Duglas";
        final String costa = "Costa";
        customerDto.setFirstname(duglas);
        customerDto.setLastname(costa);

        CustomerDTO returnDto = new CustomerDTO();
        returnDto.setLastname(customerDto.getLastname());
        returnDto.setFirstname(customerDto.getFirstname());
        returnDto.setCustomerUrl("/api/v1/customers/1");

        when(customerService.createNewCustomer(customerDto)).thenReturn(returnDto);
        mvc.perform(post("/api/v1/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDto)))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.firstname", equalTo(duglas)))
                .andExpect(jsonPath("$.lastname", equalTo(costa)))
                .andExpect(jsonPath("$.customerUrl", equalTo("/api/v1/customers/1")))
        ;
    }
}
