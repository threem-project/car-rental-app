package com.threem.carrental.sampleDataInitializer.dataSamples.genericGenerators;

import com.threem.carrental.app.utilities.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author marek_j on 2018-06-29
 */
public class PersonalDataGenerator {

    private Random random = new Random();
    private PasswordEncoder passwordEncoder = new PasswordEncoder();

    public String generateFirstName() {
        List<String> names = Arrays.asList("Anna","Maria","Katarzyna","Małgorzata","Agnieszka","Krystyna","Barbara","Ewa",
                "Elżbieta","Zofia","Janina","Teresa","Joanna","Magdalena","Monika","Jadwiga",
                "Danuta","Irena","Halina","Helena","Jan","Andrzej","Piotr","Krzysztof",
                "Stanisław","Tomasz","Paweł","Józef","Marcin","Marek","Michał","Grzegorz",
                "Jerzy","Tadeusz","Adam","Łukasz","Zbigniew","Ryszard","Dariusz","Henryk");
        Integer index = random.nextInt(names.size());
        return names.get(index);
    }

    public String generateLastName() {
        List<String> names = Arrays.asList("Bentkowski","Misiewicz","Florczyk","Ginter","Orzeł","Krasowski","Garstka",
                "Gawlak","Małkowski","Palka","Moskwa","Majcher","Łukaszczyk","Kujawa","Pestka",
                "Zalas","Mickiewicz","Ptaszyński","Kocur","Kondracki","Feliks","Wiewióra",
                "Adamkiewicz","Jaskulski","Pruś","Zdrojewski","Kuźnicki","Lipski","Krzak",
                "Branicki","Stolc","Kudliński","Jacewicz","Starzec","Sitkowski","Szafarczyk",
                "Lenard","Perz","Błażewicz","Klimaszewski","Kołakowski","Młodawski","Kubera",
                "Gąsiorek","Wiciński","Przyborowski","Wilga","Toporowski","Sobociński","Kaptur");
        Integer index = random.nextInt(names.size());
        return names.get(index);
    }

    public String generatePassword(String firstName, String lastName, String email) {
        String password = firstName+lastName+email;
        password = passwordEncoder.encode(password);
        return password;
    }

    public String generateEmail(String firstName, String lastName, String domain) {
        StringBuilder sb = new StringBuilder()
                .append(firstName)
                .append(".")
                .append(lastName)
                .append("@")
                .append(domain);
        return sb.toString();
    }

    public String generateDomainForEmail() {
        List<String> domains = Arrays.asList("fakeGmail.com","fakeWp.pl","fakeOnet.pl","fakeMailinator.com");
        Integer index = random.nextInt(domains.size());
        return domains.get(index);
    }

    public String generateDriverLicenseNumber() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0;i<5;i++) {
            stringBuilder.append(random.nextInt(9));
        }
        stringBuilder.append("/");

        for (int i=0;i<2;i++) {
            stringBuilder.append(random.nextInt(9));
        }
        stringBuilder.append("/");

        for (int i=0;i<4;i++) {
            stringBuilder.append(random.nextInt(9));
        }
        return stringBuilder.toString();
    }

    public String peselDummyGenerator() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0;i<12;i++) {
            stringBuilder.append(random.nextInt(9));
        }
        return stringBuilder.toString();
    }

    public String documentIdGenerator() {

        List<Character> characters = Arrays.asList('A','B','C','D','E','F','G');

        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0;i<3;i++) {
            Integer index = random.nextInt(characters.size());
            stringBuilder.append(characters.get(index));
        }

        for (int i=0;i<6;i++) {
            stringBuilder.append(random.nextInt(9));
        }
        return stringBuilder.toString();
    }


}
