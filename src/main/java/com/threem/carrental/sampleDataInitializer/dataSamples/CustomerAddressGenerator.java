package com.threem.carrental.sampleDataInitializer.dataSamples;

import com.threem.carrental.app.model.entity.AddressCustomerEntity;
import com.threem.carrental.app.model.entity.enumTypes.CustomerTypeEnum;
import com.threem.carrental.sampleDataInitializer.dataSamples.genericGenerators.AddressDataGenerator;

/**
 * @author marek_j on 2018-06-29
 */
public class CustomerAddressGenerator {

    private AddressDataGenerator addressDataGenerator = new AddressDataGenerator();

    public AddressCustomerEntity generate(CustomerTypeEnum customerTypeEnum) {

        String companyName = null;
        String taxId = null;

        if (customerTypeEnum.equals(CustomerTypeEnum.CORPORATE)) {
            companyName = addressDataGenerator.generateCompanyName();
            taxId = addressDataGenerator.generateTaxId();
        }

        return AddressCustomerEntity.builder()
                .building(addressDataGenerator.generateBuildingNumber())
                .city(addressDataGenerator.generateRandomCity())
                .country("Poland")
                .companyName(companyName)
                .taxId(taxId)
                .street(addressDataGenerator.generateStreet())
                .zipCode(addressDataGenerator.generateZipCode())
                .build();
    }

}
