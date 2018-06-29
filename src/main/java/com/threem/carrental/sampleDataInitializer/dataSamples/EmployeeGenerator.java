package com.threem.carrental.sampleDataInitializer.dataSamples;

import com.threem.carrental.app.model.entity.BranchEntity;
import com.threem.carrental.app.model.entity.EmployeeEntity;
import com.threem.carrental.app.repository.BranchRepository;
import com.threem.carrental.app.repository.EmployeeRepository;
import com.threem.carrental.sampleDataInitializer.dataSamples.genericGenerators.PersonalDataGenerator;
import com.threem.carrental.sampleDataInitializer.dataSamples.genericGenerators.RandomEnumsAssigner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author marek_j on 2018-06-02
 */
public class EmployeeGenerator {

    private Random random = new Random();
    private PersonalDataGenerator personalDataGenerator = new PersonalDataGenerator();
    private RandomEnumsAssigner randomEnumsAssigner = new RandomEnumsAssigner();
    private String domain;
    private EmployeeRepository employeeRepository;
    private BranchRepository branchRepository;

    public EmployeeGenerator(String domain, EmployeeRepository employeeRepository, BranchRepository branchRepository) {
        this.employeeRepository = employeeRepository;
        this.branchRepository = branchRepository;
        this.domain = domain;
    }

    public List<EmployeeEntity> generateAndSaveEmployees(Integer numberOfSamples) {
        List<BranchEntity> branchList = branchRepository.findAll();
        List<EmployeeEntity> entities = new ArrayList<>();
        for (int i = 0; i < numberOfSamples; i++) {
            EmployeeEntity employeeEntity = generateRandomEmployee();
            Integer index = random.nextInt(branchList.size());
            BranchEntity branchEntity = branchList.get(index);
            employeeEntity.setBranch(branchEntity);
            employeeRepository.save(employeeEntity);
            entities.add(employeeEntity);
        }
        return entities;
    }

    private EmployeeEntity generateRandomEmployee() {
        String firstName = personalDataGenerator.generateFirstName();
        String lastName = personalDataGenerator.generateLastName();
        String email = personalDataGenerator.generateEmail(firstName,lastName, domain);
        String password = personalDataGenerator.generatePassword(firstName,lastName,email);

        return EmployeeEntity.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .role(randomEnumsAssigner.assignEmployeeRole())
                .status(randomEnumsAssigner.assignEmployeeStatus())
                .build();
    }

}
