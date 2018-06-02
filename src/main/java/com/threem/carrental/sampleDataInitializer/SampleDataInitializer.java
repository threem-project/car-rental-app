package com.threem.carrental.sampleDataInitializer;

import com.threem.carrental.app.model.entity.AddressBranchEntity;
import com.threem.carrental.app.model.entity.MainOfficeEntity;
import com.threem.carrental.app.repository.AddressBranchRepository;
import com.threem.carrental.app.repository.EmployeeRepository;
import com.threem.carrental.app.repository.MainOfficeRepository;
import com.threem.carrental.sampleDataInitializer.dataSamples.AddressBranchGenerator;
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

    private final Integer ADDRES_BRANCH_QTY = 20;
    private final Integer EMPLOYEES_QTY = 50;

    private final MainOfficeRepository mainOfficeRepository;
    private final AddressBranchRepository addressBranchRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public SampleDataInitializer(MainOfficeRepository mainOfficeRepository, AddressBranchRepository addressBranchRepository, EmployeeRepository employeeRepository) {
        this.mainOfficeRepository = mainOfficeRepository;
        this.addressBranchRepository = addressBranchRepository;
        this.employeeRepository = employeeRepository;
    }

    @PostConstruct
    void init() {
        generateAndSaveMainOffice();
        generateAndSaveAddressBranch();
        generateAndSaveEmployees();
    }

    private void generateAndSaveMainOffice() {
        MainOfficeGenerator officeGenerator = new MainOfficeGenerator();
        MainOfficeEntity mainOfficeEntity = officeGenerator.generate();
        mainOfficeRepository.save(mainOfficeEntity);
    }

    private void generateAndSaveAddressBranch() {
        AddressBranchGenerator addressGenerator = new AddressBranchGenerator();
        List<AddressBranchEntity> addressBranchEntityList = addressGenerator.generate(ADDRES_BRANCH_QTY);
        addressBranchRepository.saveAll(addressBranchEntityList);
    }

    private void generateAndSaveEmployees() {

    }
}
