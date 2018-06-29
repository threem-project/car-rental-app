package com.threem.carrental.sampleDataInitializer;

import com.threem.carrental.app.model.entity.MainOfficeEntity;
import com.threem.carrental.app.repository.*;
import com.threem.carrental.sampleDataInitializer.dataSamples.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author misza_lemko on 28.05.2018
 * @project car-rental-app
 */
@Configuration
public class SampleDataInitializer {

    private final Integer BRANCH_QTY = 50;
    private final Integer EMPLOYEES_QTY = 150;
    private final Integer CAR_QTY = 30;
    private final Integer CUSTOMERS_QTY = 100;
    private final String DOMAIN_NAME = "car-rental-app.com";

    private final MainOfficeRepository mainOfficeRepository;
    private final AddressBranchRepository addressBranchRepository;
    private final EmployeeRepository employeeRepository;
    private final BranchRepository branchRepository;
    private final CarRepository carRepository;
    private final EquipmentRepository equipmentRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public SampleDataInitializer(MainOfficeRepository mainOfficeRepository, AddressBranchRepository addressBranchRepository, EmployeeRepository employeeRepository, BranchRepository branchRepository, CarRepository carRepository, EquipmentRepository equipmentRepository, CustomerRepository customerRepository) {
        this.mainOfficeRepository = mainOfficeRepository;
        this.addressBranchRepository = addressBranchRepository;
        this.employeeRepository = employeeRepository;
        this.branchRepository = branchRepository;
        this.carRepository = carRepository;
        this.equipmentRepository = equipmentRepository;
        this.customerRepository = customerRepository;
    }

    @PostConstruct
    void init() {
        generateAndSaveMainOffice();
        generateAndSaveBranch();
        generateAndSaveEmployees();
        generateAndSaveCars();
        generateAndSaveCustomers();
    }

    private void generateAndSaveMainOffice() {
        MainOfficeGenerator officeGenerator = new MainOfficeGenerator(DOMAIN_NAME);
        MainOfficeEntity mainOfficeEntity = officeGenerator.generate();
        mainOfficeRepository.save(mainOfficeEntity);
    }

    private void generateAndSaveBranch() {
        BranchGenerator branchGenerator = new BranchGenerator(branchRepository, addressBranchRepository);
        branchGenerator.generateAndSaveBranchAndAddress(BRANCH_QTY);
    }

    private void generateAndSaveEmployees() {
        EmployeeGenerator employeeGenerator = new EmployeeGenerator(DOMAIN_NAME, employeeRepository, branchRepository);
        employeeGenerator.generateAndSaveEmployees(EMPLOYEES_QTY);
    }

    private void generateAndSaveCars() {
        CarGenerator carGenerator = new CarGenerator(branchRepository,carRepository,equipmentRepository);
        carGenerator.generateAndSaveEquipmentBeforeCars();
        carGenerator.generateAndSaveCars(CAR_QTY);
    }

    private void generateAndSaveCustomers() {
        CustomerGenerator customerGenerator = new CustomerGenerator(customerRepository);
        customerGenerator.generateAndSaveCustomers(CUSTOMERS_QTY);
    }
}