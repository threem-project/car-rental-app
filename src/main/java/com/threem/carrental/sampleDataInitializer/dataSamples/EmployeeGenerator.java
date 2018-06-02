package com.threem.carrental.sampleDataInitializer.dataSamples;

import com.threem.carrental.app.model.entity.EmployeeEntity;
import com.threem.carrental.app.model.entity.enumTypes.EmployeeRoleEnum;
import com.threem.carrental.app.model.entity.enumTypes.EmployeeStatusEnum;
import com.threem.carrental.app.utilities.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author marek_j on 2018-06-02
 */
public class EmployeeGenerator {

    private Random random = new Random();
    private PasswordEncoder passwordEncoder = new PasswordEncoder();

    public List<EmployeeEntity> generate(Integer numberOfSamples) {
        List<EmployeeEntity> entities = new ArrayList<>();
        for (int i = 0; i < numberOfSamples; i++) {
            entities.add(generateRandomEmployee());
        }
        return entities;
    }

    private EmployeeEntity generateRandomEmployee() {
        String firstName = generateFirstName();
        String lastName = generateLastName();
        String email = generateEmail(firstName,lastName);
        String password = generatePassword(firstName,lastName,email);

        return new EmployeeEntity().builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .role(assignRole())
                .status(assignStatus())
                .build();
    }

    private String generateFirstName() {
    }

    private String generateLastName() {
    }

    private String generateEmail(String firstName, String lastName) {
    }

    private String generatePassword(String firstName, String lastName, String email) {
    }

    private EmployeeStatusEnum assignStatus() {
    }

    private EmployeeRoleEnum assignRole() {
    }
}
