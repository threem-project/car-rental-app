package com.threem.carrental.sampleDataInitializer.dataSamples;

import com.threem.carrental.app.model.entity.MainOfficeEntity;

/**
 * @author marek_j on 2018-06-02
 */
public class MainOfficeGenerator {

    public MainOfficeEntity generate() {
        return new MainOfficeEntity().builder()
                .name("Wypożyczalnia Pieska Leszka")
                .domain("wypozyczalna-pieska-leszka")
                .address("Warszawa, ul. Psia 100")
                .phone("111-222-333")
                .email("wypozyczalnia@pieskaleszka.com")
                .build();
    }
}
