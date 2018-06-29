package com.threem.carrental.sampleDataInitializer.dataSamples;

import com.threem.carrental.app.model.entity.AddressCustomerEntity;
import com.threem.carrental.app.model.entity.CustomerEntity;
import com.threem.carrental.app.model.entity.enumTypes.CustomerAccountStatusEnum;
import com.threem.carrental.app.model.entity.enumTypes.CustomerTypeEnum;
import com.threem.carrental.app.repository.CustomerRepository;
import com.threem.carrental.sampleDataInitializer.dataSamples.genericGenerators.AddressDataGenerator;
import com.threem.carrental.sampleDataInitializer.dataSamples.genericGenerators.PersonalDataGenerator;
import com.threem.carrental.sampleDataInitializer.dataSamples.genericGenerators.RandomEnumsAssigner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author marek_j on 2018-06-29
 */
public class CustomerGenerator {

    private CustomerRepository customerRepository;
    private PersonalDataGenerator personalDataGenerator = new PersonalDataGenerator();
    private AddressDataGenerator addressDataGenerator = new AddressDataGenerator();
    private RandomEnumsAssigner enumsAssigner = new RandomEnumsAssigner();
    private CustomerAddressGenerator customerAddressGenerator = new CustomerAddressGenerator();

    public CustomerGenerator(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerEntity> generateAndSaveCustomers(Integer numberOfSamples) {
        List<CustomerEntity> entities = new ArrayList<>();
        for (int i = 0; i < numberOfSamples; i++) {
            CustomerEntity customerEntity = generateRandomCustomer();
            customerRepository.save(customerEntity);
            entities.add(customerEntity);
        }
        return entities;
    }

    private CustomerEntity generateRandomCustomer() {
        String firstName = personalDataGenerator.generateFirstName();
        String lastName = personalDataGenerator.generateLastName();
        String phone = addressDataGenerator.generatePhoneNumber();
        String emailDomain = personalDataGenerator.generateDomainForEmail();
        String email = personalDataGenerator.generateEmail(firstName,lastName, emailDomain);
        String password = personalDataGenerator.generatePassword(firstName,lastName,email);
        String driverLicenseNumber = personalDataGenerator.generateDriverLicenseNumber();
        String pesel = personalDataGenerator.peselDummyGenerator();
        String documentId = personalDataGenerator.documentIdGenerator();
        CustomerTypeEnum customerTypeEnum = enumsAssigner.assignCustomerStatus();
        CustomerAccountStatusEnum customerAccountStatusEnum = enumsAssigner.assignCustomerAccountStatus();
        AddressCustomerEntity address = customerAddressGenerator.generate(customerTypeEnum);

        return CustomerEntity.builder()
                .id(null)
                .firstName(firstName)
                .lastName(lastName)
                .phone(phone)
                .email(email)
                .password(password)
                .driverLicense(driverLicenseNumber)
                .pesel(pesel)
                .documentId(documentId)
                .address(address)
                .accountStatus(customerAccountStatusEnum)
                .status(customerTypeEnum)
                .build();
    }

}