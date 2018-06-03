package com.threem.carrental.sampleDataInitializer.dataSamples;

import com.threem.carrental.app.model.entity.MainOfficeEntity;

/**
 * @author marek_j on 2018-06-02
 */
public class MainOfficeGenerator {

    private final String domain;

    public MainOfficeGenerator(String domain_name) {
        this.domain = domain_name;
    }

    public MainOfficeEntity generate() {
        return MainOfficeEntity.builder()
                .name("Wypożyczalnia Pieska Leszka")
                .domain(domain)
                .address("Warszawa, ul. Psia 100")
                .phone("111-222-333")
                .email("car-rental-app.com")
                .build();
    }
}
