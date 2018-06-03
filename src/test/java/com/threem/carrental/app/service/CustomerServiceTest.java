package com.threem.carrental.app.service;

import com.threem.carrental.app.model.entity.AddressCustomerEntity;
import com.threem.carrental.app.model.entity.CustomerEntity;
import com.threem.carrental.app.model.entity.enumTypes.CustomerStatusEnum;
import com.threem.carrental.app.utilities.PasswordEncoder;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * @author misza_lemko on 03.06.2018
 * @project car-rental-app
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Rollback
@Transactional
@ActiveProfiles("test")
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void shouldCreateAndReturnCustomerEntity() throws Exception {
        //given
        CustomerEntity customerEntity = CustomerEntity.builder()
                .id(null)
                .firstName("Mirosłąw")
                .lastName("Dąbrowski")
                .phone("111-222-333")
                .email("mail@mail.com")
                .password("very_hard_encoded_password")
                .pesel("pesel")
                .documentId("documentId")
                .driverLicense("driverLicenseNu")
                .passportId("passportId")
                .status(CustomerStatusEnum.ACTIVE)
                .address(null)
                .build();
        AddressCustomerEntity addressCustomerEntity = AddressCustomerEntity.builder()
                .id(null)
                .city("Warsaw")
                .street("Aleje Jerozolimskie")
                .building("67/2")
                .zipCode("02-000")
                .country("Poland")
                .taxId("taxId")
                .companyName("companyName")
                .build();
        customerEntity.setAddress(addressCustomerEntity);
        //when
        String encodePassword = passwordEncoder.encode(customerEntity.getPassword());
        Optional<CustomerEntity> customerEntityFromDb = customerService.createCustomerEntity(customerEntity);
        //then
        Assertions.assertThat(customerEntityFromDb.get())
                .hasFieldOrPropertyWithValue("id", customerEntityFromDb.get().getId())
                .hasFieldOrPropertyWithValue("firstName", customerEntity.getFirstName())
                .hasFieldOrPropertyWithValue("lastName", customerEntity.getLastName())
                .hasFieldOrPropertyWithValue("phone", customerEntity.getPhone())
                .hasFieldOrPropertyWithValue("email", customerEntity.getEmail())
                .hasFieldOrPropertyWithValue("password", encodePassword)
                .hasFieldOrPropertyWithValue("pesel", customerEntity.getPesel())
                .hasFieldOrPropertyWithValue("documentId", customerEntity.getDocumentId())
                .hasFieldOrPropertyWithValue("driverLicense", customerEntity.getDriverLicense())
                .hasFieldOrPropertyWithValue("passportId", customerEntity.getPassportId())
                .hasFieldOrPropertyWithValue("status", customerEntity.getStatus());
        Assertions.assertThat(customerEntityFromDb.get().getAddress())
                .hasFieldOrPropertyWithValue("id", customerEntityFromDb.get().getAddress().getId())
                .hasFieldOrPropertyWithValue("city", addressCustomerEntity.getCity())
                .hasFieldOrPropertyWithValue("street", addressCustomerEntity.getStreet())
                .hasFieldOrPropertyWithValue("building", addressCustomerEntity.getBuilding())
                .hasFieldOrPropertyWithValue("zipCode", addressCustomerEntity.getZipCode())
                .hasFieldOrPropertyWithValue("country", addressCustomerEntity.getCountry())
                .hasFieldOrPropertyWithValue("taxId", addressCustomerEntity.getTaxId())
                .hasFieldOrPropertyWithValue("companyName", addressCustomerEntity.getCompanyName());
    }
}