package com.threem.carrental.sampleDataInitializer;

import com.threem.carrental.app.model.entity.AddressBranchEntity;
import com.threem.carrental.app.model.entity.MainOfficeEntity;
import com.threem.carrental.app.repository.AddressBranchRepository;
import com.threem.carrental.app.repository.MainOfficeRepository;
import com.threem.carrental.sampleDataInitializer.dataSamples.AddressBranchDataSample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author misza_lemko on 28.05.2018
 * @project car-rental-app
 */
@Configuration
public class SampleDataInitializer {

    private final Integer NUMBER_OF_BRANCH_ADDRESS_ENTITIES=20;

    private final MainOfficeRepository mainOfficeRepository;
    private final AddressBranchRepository addressBranchRepository;

    @Autowired
    public SampleDataInitializer(MainOfficeRepository mainOfficeRepository, AddressBranchRepository addressBranchRepository) {
        this.mainOfficeRepository = mainOfficeRepository;
        this.addressBranchRepository = addressBranchRepository;
    }

    @PostConstruct
    void init() {
        generateAndSaveMainOfficeEntity();
        generateAndSaveAddressBranchEntites();
    }

    private void generateAndSaveAddressBranchEntites() {
        List<AddressBranchEntity> addressBranchEntityList = AddressBranchDataSample
                .generateBranchAddressEntities(NUMBER_OF_BRANCH_ADDRESS_ENTITIES);
        addressBranchRepository.saveAll(addressBranchEntityList);
    }

    private void generateAndSaveMainOfficeEntity() {
        mainOfficeRepository.save(MainOfficeEntity.builder()
                .name("Google")
                .domain("google.com")
                .address("Mountain View, California, U.S.")
                .phone("111-222-333")
                .email("office@google.com")
                .build());
    }
}
