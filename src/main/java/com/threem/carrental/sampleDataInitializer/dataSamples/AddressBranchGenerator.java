package com.threem.carrental.sampleDataInitializer.dataSamples;

import com.threem.carrental.app.model.entity.AddressBranchEntity;
import com.threem.carrental.sampleDataInitializer.dataSamples.genericGenerators.AddressDataGenerator;

/**
 * @author marek_j on 2018-06-01
 */
public class AddressBranchGenerator {

    private AddressDataGenerator addressDataGenerator = new AddressDataGenerator();

    public AddressBranchEntity generate() {
        return AddressBranchEntity.builder()
                .building(addressDataGenerator.generateBuildingNumber())
                .city(addressDataGenerator.generateRandomCity())
                .country("Poland")
                .phone(addressDataGenerator.generatePhoneNumber())
                .street(addressDataGenerator.generateStreet())
                .zipCode(addressDataGenerator.generateZipCode())
                .build();
    }
}
