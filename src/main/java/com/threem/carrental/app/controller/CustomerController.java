package com.threem.carrental.app.controller;

import com.threem.carrental.app.model.entity.CustomerEntity;
import com.threem.carrental.app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author misza_lemko on 03.06.2018
 * @project car-rental-app
 */
@RestController
@RequestMapping("customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<CustomerEntity> createCustomer(@Validated @RequestBody CustomerEntity customerEntity) {
        Optional<CustomerEntity> customerEntityFromService = customerService.createCustomerEntity(customerEntity);
        if (customerEntityFromService.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(customerEntityFromService.get());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        //todo test do contoller
    }
}
