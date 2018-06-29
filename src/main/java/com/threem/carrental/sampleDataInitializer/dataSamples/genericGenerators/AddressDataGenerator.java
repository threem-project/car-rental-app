package com.threem.carrental.sampleDataInitializer.dataSamples.genericGenerators;

import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author marek_j on 2018-06-29
 */
public class AddressDataGenerator {

    private Random random = new Random();

    public String generateRandomCity() {
        List<String> cities = Arrays.asList("Warszwa", "Kraków", "Łódź", "Poznań");
        Integer index = random.nextInt(cities.size());
        return cities.get(index);
    }

    public String generateBuildingNumber() {
        Integer buildingNumber = random.nextInt(300);
        if (buildingNumber == 0) {
            buildingNumber = +100;
        }
        return buildingNumber.toString();
    }

    public String generateStreet() {
        Integer streetSufix = random.nextInt(300);
        if (streetSufix == 0) {
            streetSufix = +100;
        }
        return "Ulica " + streetSufix + " testowa";
    }

    public String generatePhoneNumber() {
        String phoneNumber = "";
        for (int i = 1; i < 10; i++) {
            Integer randomInt = random.nextInt(10);
            if (i == 4 || i == 7) {
                phoneNumber += "-";
            }
            phoneNumber += String.valueOf(randomInt);
        }
        return phoneNumber;
    }

    public String generateZipCode() {
        String zipCode = "";
        for (int i = 1; i < 6; i++) {
            Integer randomInt = random.nextInt(10);
            if (i == 3) {
                zipCode += "-";
            }
            zipCode += String.valueOf(randomInt);
        }
        return zipCode;
    }

    public String generateCompanyName() {
        List<String> prefix = Arrays.asList("P.H.U.", "Spółdzielnia", "Przedsiębiorstwo", "");
        List<String> firstPart = Arrays.asList("Informatyka", "Mechanika", "Hydraulika", "Edukacja", "Budownictwo",
                                               "Piekarnia", "Ciastkarnia", "Chłodnictwo", "Szkolenia", "Drukarnia");
        List<String> secondPart = Arrays.asList("Service", "Usługi", "Handel", "dla Każdego", "na 100%");
        List<String> suffix = Arrays.asList("S.C.", "i bracia", "i wspólnicy", "sp. z o.o.", "S.K.", "");
        StringBuilder stringBuilder = new StringBuilder();

        Integer randomPrefixIndex = random.nextInt(prefix.size());
        String prefixString = prefix.get(randomPrefixIndex);
        if (!StringUtils.isEmpty(prefixString)) {
            stringBuilder.append(prefixString);
            stringBuilder.append(" ");
        }

        Integer randomFirstPartIndex = random.nextInt(prefix.size());
        String randomFirstPartString = firstPart.get(randomFirstPartIndex);
        if (!StringUtils.isEmpty(randomFirstPartString)) {
            stringBuilder.append(randomFirstPartString);
            stringBuilder.append(" ");
        }

        Integer secondPartIndex = random.nextInt(prefix.size());
        String randomSecondPartString = secondPart.get(secondPartIndex);
        if (!StringUtils.isEmpty(randomSecondPartString)) {
            stringBuilder.append(randomSecondPartString);
            stringBuilder.append(" ");
        }

        Integer suffixIndex = random.nextInt(prefix.size());
        String randomSuffixString = suffix.get(suffixIndex);
        if (!StringUtils.isEmpty(randomSuffixString)) {
            stringBuilder.append(randomSuffixString);
            stringBuilder.append(" ");
        }

        return stringBuilder.toString();
    }

    public String generateTaxId() {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i=0;i<3;i++) {
                stringBuilder.append(random.nextInt(9));
            }
            stringBuilder.append("-");

            for (int i=0;i<2;i++) {
                stringBuilder.append(random.nextInt(9));
            }
            stringBuilder.append("-");

            for (int i=0;i<2;i++) {
                stringBuilder.append(random.nextInt(9));
            }
            stringBuilder.append("-");

            for (int i=0;i<3;i++) {
                stringBuilder.append(random.nextInt(9));
            }
            return stringBuilder.toString();
    }
}
