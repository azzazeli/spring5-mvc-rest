package com.alexm.spring.spring5mvcrest.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author AlexM
 * Date: 3/5/20
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerListDTO {
    private List<CustomerDTO> customers;
}
