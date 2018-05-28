package com.threem.carrental.sampleDataInitializer;

import com.threem.carrental.app.model.entity.MainOfficeEntity;
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

    @Autowired
    public SampleDataInitializer(MainOfficeRepository mainOfficeRepository) {
        this.mainOfficeRepository = mainOfficeRepository;
    }

    @PostConstruct
    void init() {
        mainOfficeRepository.save(MainOfficeEntity.builder()
                .name("Google")
                .domain("google.com")
                .address("Mountain View, California, U.S.")
                .phone("111-222-333")
                .email("office@google.com")
                .build());
    }
}
