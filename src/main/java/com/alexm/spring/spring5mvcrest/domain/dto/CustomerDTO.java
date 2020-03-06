package com.alexm.spring.spring5mvcrest.domain.dto;

import lombok.Data;

/**
 * @author AlexM
 * Date: 3/5/20
 **/
@Data
public class CustomerDTO {
    private Long id;
    private String firtsname;
    private String lastname;
    private String customerUrl;
}
