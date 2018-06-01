package com.threem.carrental.sampleDataInitializer.dataSamples;

import com.threem.carrental.app.model.entity.AddressBranchEntity;
import com.threem.carrental.app.model.entity.BranchEntity;

import java.util.List;
import java.util.Random;

/**
 * @author marek_j on 2018-06-01
 */
public class BranchAndAddressDataSamples {

    private static Random random = new Random();

    public List<BranchEntity> generateBranchEntites(Integer numberOfEntitiesToGenerate) {
        for ()
        return null;
    }

    private static class GenerateAddress {

        private static AddressBranchEntity generateRandomAddressWarszawa() {
            return new AddressBranchEntity().builder()
                    .building(generateBuildingNumber())
                    .city("Warszawa")
                    .country("Poland")
                    .phone(generatePhoneNumber())
                    .street("Ulica testowa")
                    .zipCode(generateZipCode())
                    .build();
        }

        private static String generateBuildingNumber() {
            Integer buildingNumber = random.nextInt(300);
            if (buildingNumber==0) {
                buildingNumber=+100;
            } else if (buildingNumber<0) {
                buildingNumber=buildingNumber*(-1);
            }
            return buildingNumber.toString();
        }

        private static String generatePhoneNumber() {
            String phoneNumber = null;
            for (int i=1;i<10;i++) {
                Integer randomInt = random.nextInt(10);
                if (randomInt<0) {
                    randomInt*=(-1);
                }
                if (i==4 || i==8) {
                    phoneNumber+="-";
                }
                phoneNumber+=String.valueOf(randomInt);
            }
            return phoneNumber;
        }

        private static String generateZipCode() {
            String zipCode = null;
            for (int i=1;i<6;i++) {
                Integer randomInt = random.nextInt(10);
                if (randomInt<0) {
                    randomInt*=(-1);
                }
                if (i==3) {
                    zipCode+="-";
                }
                zipCode+=String.valueOf(randomInt);
            }
            return zipCode;
        }
    }

}
