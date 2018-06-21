package com.threem.carrental.sampleDataInitializer;

import com.threem.carrental.app.model.entity.AddressBranchEntity;
import com.threem.carrental.app.model.entity.BranchEntity;
import com.threem.carrental.app.model.entity.MainOfficeEntity;
import com.threem.carrental.app.repository.*;
import com.threem.carrental.sampleDataInitializer.dataSamples.BranchGenerator;
import com.threem.carrental.sampleDataInitializer.dataSamples.CarGenerator;
import com.threem.carrental.sampleDataInitializer.dataSamples.EmployeeGenerator;
import com.threem.carrental.sampleDataInitializer.dataSamples.MainOfficeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author misza_lemko on 28.05.2018
 * @project car-rental-app
 */
@Configuration
public class SampleDataInitializer {

    private final Integer BRANCH_QTY = 50;
    private final Integer EMPLOYEES_QTY = 150;
    private final Integer CAR_QTY = 30;
    private final String DOMAIN_NAME = "car-rental-app.com";

    private final MainOfficeRepository mainOfficeRepository;
    private final AddressBranchRepository addressBranchRepository;
    private final EmployeeRepository employeeRepository;
    private final BranchRepository branchRepository;
    private final CarRepository carRepository;

    @Autowired
    public SampleDataInitializer(MainOfficeRepository mainOfficeRepository, AddressBranchRepository addressBranchRepository, EmployeeRepository employeeRepository, BranchRepository branchRepository, CarRepository carRepository) {
        this.mainOfficeRepository = mainOfficeRepository;
        this.addressBranchRepository = addressBranchRepository;
        this.employeeRepository = employeeRepository;
        this.branchRepository = branchRepository;
        this.carRepository = carRepository;
    }

    @PostConstruct
    void init() {
        generateAndSaveMainOffice();
        generateAndSaveBranch();
        generateAndSaveEmployees();
        generateAndSaveCars();
    }

    private void generateAndSaveMainOffice() {
        MainOfficeGenerator officeGenerator = new MainOfficeGenerator(DOMAIN_NAME);
        MainOfficeEntity mainOfficeEntity = officeGenerator.generate();
        mainOfficeRepository.save(mainOfficeEntity);
    }

    private void generateAndSaveBranch() {
        List<AddressBranchEntity> addressBranchList = addressBranchRepository.findAll();
        BranchGenerator branchGenerator = new BranchGenerator(branchRepository, addressBranchRepository);
        branchGenerator.generateAndSaveBranchAndAddress(BRANCH_QTY);
    }

    private void generateAndSaveEmployees() {
        List<BranchEntity> branchEntityList = branchRepository.findAll();
        EmployeeGenerator employeeGenerator = new EmployeeGenerator(DOMAIN_NAME, employeeRepository, branchRepository);
        employeeGenerator.generateAndSaveEmployees(EMPLOYEES_QTY);
    }

    private void generateAndSaveCars() {
        CarGenerator carGenerator = new CarGenerator(branchRepository,carRepository);
        carGenerator.generateAndSaveCars(CAR_QTY);
    }
}