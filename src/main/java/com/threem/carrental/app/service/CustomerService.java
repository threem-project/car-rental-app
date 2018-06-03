package com.threem.carrental.app.service;

import com.threem.carrental.app.model.entity.CustomerEntity;
import com.threem.carrental.app.repository.CustomerRepository;
import com.threem.carrental.app.utilities.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author misza_lemko on 03.06.2018
 * @project car-rental-app
 */
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<CustomerEntity> createCustomerEntity(CustomerEntity customerEntity) {
        if(customerEntity!=null) {
            String encodePassword = passwordEncoder.encode(customerEntity.getPassword());
            customerEntity.setPassword(encodePassword);
        }
        Optional<CustomerEntity> customerEntityFromDb = Optional.of(customerRepository.save(customerEntity));
        customerEntityFromDb.get().getAddress();
        return customerEntityFromDb;
    }
}
