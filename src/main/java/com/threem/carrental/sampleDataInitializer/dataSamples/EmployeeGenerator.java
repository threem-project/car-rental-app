package com.threem.carrental.sampleDataInitializer.dataSamples;

import com.threem.carrental.app.model.entity.BranchEntity;
import com.threem.carrental.app.model.entity.EmployeeEntity;
import com.threem.carrental.app.model.entity.enumTypes.EmployeeRoleEnum;
import com.threem.carrental.app.model.entity.enumTypes.EmployeeStatusEnum;
import com.threem.carrental.app.repository.BranchRepository;
import com.threem.carrental.app.repository.EmployeeRepository;
import com.threem.carrental.app.utilities.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author marek_j on 2018-06-02
 */
public class EmployeeGenerator {

    private Random random = new Random();
    private PasswordEncoder passwordEncoder = new PasswordEncoder();
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
        String firstName = generateFirstName();
        String lastName = generateLastName();
        String email = generateEmail(firstName,lastName);
        String password = generatePassword(firstName,lastName,email);

        return EmployeeEntity.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .role(assignRole())
                .status(assignStatus())
                .build();
    }

    private String generateFirstName() {
        List<String> names = Arrays.asList("Anna","Maria","Katarzyna","Małgorzata","Agnieszka","Krystyna","Barbara","Ewa",
                                            "Elżbieta","Zofia","Janina","Teresa","Joanna","Magdalena","Monika","Jadwiga",
                                            "Danuta","Irena","Halina","Helena","Jan","Andrzej","Piotr","Krzysztof",
                                            "Stanisław","Tomasz","Paweł","Józef","Marcin","Marek","Michał","Grzegorz",
                                            "Jerzy","Tadeusz","Adam","Łukasz","Zbigniew","Ryszard","Dariusz","Henryk");
        Integer index = random.nextInt(names.size());
        return names.get(index);
    }

    private String generateLastName() {
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

    private String generateEmail(String firstName, String lastName) {
        StringBuilder sb = new StringBuilder()
                .append(firstName)
                .append(".")
                .append(lastName)
                .append("@")
                .append(domain);
        return sb.toString();
    }

    private String generatePassword(String firstName, String lastName, String email) {
        String password = firstName+lastName+email;
        password = passwordEncoder.encode(password);
        return password;
    }

    private EmployeeStatusEnum assignStatus() {
        List<EmployeeStatusEnum> statusEnumList = Arrays.asList(EmployeeStatusEnum.ACTIVE,EmployeeStatusEnum.NEW,EmployeeStatusEnum.DEACTIVATED);
        Integer index = random.nextInt(statusEnumList.size());
        return statusEnumList.get(index);
    }

    private EmployeeRoleEnum assignRole() {
        List<EmployeeRoleEnum> roleEnumList = Arrays.asList(EmployeeRoleEnum.REGULAR_EMPLOYEE,EmployeeRoleEnum.BRANCH_MANAGER);
        Integer index = random.nextInt(roleEnumList.size());
        return roleEnumList.get(index);
    }
}
