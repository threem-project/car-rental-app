package com.threem.carrental.factory;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author marek_j on 2018-06-24
 */
public class DummyVinGenerator {

    public static String generateVin() {
        final Integer VIN_LENGTH = 17;
        final List<String> VIN_CHARACTERS = Arrays.asList("T","E","S","T","X","Y","Z","U","O","H","P",
                "1","2","3","4","5","6","7","8","9","0");
        Random random = new Random();
        String vin = "";

        for (int i = 0; i<VIN_LENGTH; i++) {
            Integer indexOfElement = random.nextInt(VIN_CHARACTERS.size());
            vin = vin + VIN_CHARACTERS.get(indexOfElement);
        }
        return vin;
    }
}
