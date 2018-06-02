package com.threem.carrental.sampleDataInitializer;

import com.threem.carrental.app.model.dto.AddressBranchDto;
import com.threem.carrental.app.model.dto.BranchDto;
import com.threem.carrental.app.model.entity.AddressBranchEntity;
import com.threem.carrental.app.model.entity.BranchEntity;
import com.threem.carrental.app.model.entity.MainOfficeEntity;
import com.threem.carrental.app.model.entity.enumTypes.BranchStatusEnum;
import com.threem.carrental.app.repository.BranchRepository;
import com.threem.carrental.app.repository.MainOfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author misza_lemko on 28.05.2018
 * @project car-rental-app
 */
@Configuration
public class SampleDataInitializer {

    private final MainOfficeRepository mainOfficeRepository;
    private final BranchRepository branchRepository;

    @Autowired
    public SampleDataInitializer(MainOfficeRepository mainOfficeRepository, BranchRepository branchRepository) {
        this.mainOfficeRepository = mainOfficeRepository;
        this.branchRepository = branchRepository;
    }

    @PostConstruct
    void init() {
        BranchEntity branchEntity = BranchEntity.builder()
                .status(BranchStatusEnum.OPENED)
                .build();
        AddressBranchEntity addressBranchEntity = AddressBranchEntity.builder()
                .city("Warsaw")
                .street("Towarowa")
                .building("20/10")
                .zipCode("02-495")
                .country("Poland")
                .phone("111-222-333")
                .build();
        branchEntity.setAddress(addressBranchEntity);
        branchRepository.save(branchEntity);
        mainOfficeRepository.save(MainOfficeEntity.builder()
                .name("Google")
                .domain("google.com")
                .address("Mountain View, California, U.S.")
                .phone("111-222-333")
                .email("office@google.com")
                .build());

    }
}
