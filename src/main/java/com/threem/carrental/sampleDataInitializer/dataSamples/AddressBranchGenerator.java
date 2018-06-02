package com.threem.carrental.sampleDataInitializer.dataSamples;

import com.threem.carrental.app.model.entity.AddressBranchEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author marek_j on 2018-06-01
 */
public class AddressBranchGenerator {

    private Random random = new Random();

    public List<AddressBranchEntity> generate(Integer numberOfSamples) {
        List<AddressBranchEntity> entities = new ArrayList<>();
        for (int i = 0; i < numberOfSamples; i++) {
            entities.add(generateRandomAddress());
        }
        return entities;
    }

    private AddressBranchEntity generateRandomAddress() {
        return new AddressBranchEntity().builder()
                .building(generateBuildingNumber())
                .city(generateRandomCity())
                .country("Poland")
                .phone(generatePhoneNumber())
                .street(generateStreet())
                .zipCode(generateZipCode())
                .build();
    }

    private String generateRandomCity() {
        List<String> cities = Arrays.asList("Warszwa", "Kraków", "Łódź", "Poznań");
        Integer index = random.nextInt(cities.size());
        return cities.get(index);
    }

    private String generateBuildingNumber() {
        Integer buildingNumber = random.nextInt(300);
        if (buildingNumber == 0) {
            buildingNumber = +100;
        } else if (buildingNumber < 0) {
            buildingNumber = buildingNumber * (-1);
        }
        return buildingNumber.toString();
    }

    private String generateStreet() {
        Integer streetSufix = random.nextInt(300);
        if (streetSufix == 0) {
            streetSufix = +100;
        } else if (streetSufix < 0) {
            streetSufix = streetSufix * (-1);
        }
        return "Ulica " + streetSufix + " testowa";
    }

    private String generatePhoneNumber() {
        String phoneNumber = "";
        for (int i = 1; i < 10; i++) {
            Integer randomInt = random.nextInt(10);
            if (randomInt < 0) {
                randomInt *= (-1);
            }
            if (i == 4 || i == 7) {
                phoneNumber += "-";
            }
            phoneNumber += String.valueOf(randomInt);
        }
        return phoneNumber;
    }

    private String generateZipCode() {
        String zipCode = "";
        for (int i = 1; i < 6; i++) {
            Integer randomInt = random.nextInt(10);
            if (randomInt < 0) {
                randomInt *= (-1);
            }
            if (i == 3) {
                zipCode += "-";
            }
            zipCode += String.valueOf(randomInt);
        }
        return zipCode;
    }
}
