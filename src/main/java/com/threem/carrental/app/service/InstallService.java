package com.threem.carrental.app.service;

import com.threem.carrental.app.model.entity.EmployeeEntity;
import com.threem.carrental.app.model.entity.MainOfficeEntity;
import com.threem.carrental.app.model.entity.enumTypes.EmployeeRoleEnum;
import com.threem.carrental.app.model.entity.enumTypes.EmployeeStatusEnum;
import com.threem.carrental.app.repository.EmployeeRepository;
import com.threem.carrental.app.repository.MainOfficeRepository;
import com.threem.carrental.app.utilities.PasswordEncoder;
import com.threem.carrental.app.model.dto.InstallDto;
import com.threem.carrental.app.model.entity.enumTypes.InstallationStatusEnum;
import com.threem.carrental.app.model.entity.InstallerEntity;
import com.threem.carrental.app.model.dto.InstallerViewDto;
import com.threem.carrental.app.repository.InstallRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author marek_j on 2018-05-08
 */

@Service
public class InstallService {

    private static final Logger LOG = LoggerFactory.getLogger(InstallService.class);
    private InstallRepository installRepository;
    private EmployeeRepository employeeRepository;
    private MainOfficeRepository mainOfficeRepository;

    public InstallService(InstallRepository installRepository, EmployeeRepository employeeRepository, MainOfficeRepository mainOfficeRepository) {
        this.installRepository = installRepository;
        this.employeeRepository = employeeRepository;
        this.mainOfficeRepository = mainOfficeRepository;
    }

    public InstallerViewDto createMainOffice(InstallDto command) {
        List<InstallerEntity> installerEntityList = installRepository.findAll();
        if (notCorrectInstallEntityList(installerEntityList)) {
            return null;
        }

        List<MainOfficeEntity> mainOfficeEntityList = mainOfficeRepository.findAll();
        if (mainOfficeAlreadyInDb(mainOfficeEntityList)) {
            return null;
        }

        EmployeeEntity employee = EmployeeEntity.builder()
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .role(EmployeeRoleEnum.OWNER)
                .branch(null)
                .status(EmployeeStatusEnum.ACTIVE)
                .password(PasswordEncoder.encode(command.getPassword()))
                .build();

        MainOfficeEntity mainOffice = MainOfficeEntity.builder()
                .name(command.getCompanyName())
                .domain(command.getDomain())
                .address(command.getAddress())
                .phone(command.getPhone())
                .build();

        InstallerEntity installer = InstallerEntity.builder()
                .installationDate(LocalDateTime.now())
                .crc("for future use")
                .license("for future use")
                .installationStatus(InstallationStatusEnum.COMPLETED)
                .build();

        employeeRepository.save(employee);
        mainOfficeRepository.save(mainOffice);
        installRepository.save(installer);

        Long mainOfficeId = getMainOfficeIdFromDb(mainOfficeRepository.findAll());
        return new InstallerViewDto(mainOfficeId,command.getCompanyName());
    }

    private boolean notCorrectInstallEntityList(List<InstallerEntity> installerEntityList) {
        boolean result = false;
        if (installerEntityList.size()>0) {
            LOG.info("InstallService - application is already installed. Can't install it once again");
            result = true;
        }
        return result;
    }

    private boolean mainOfficeAlreadyInDb(List<MainOfficeEntity> mainOfficeEntityList) {
        boolean result = false;
        if (mainOfficeEntityList.size()!=0) {
            LOG.error("Instalation not possible. Error while fetching main_office entities from db - number of entites is not equal to zero");
            result = true;
        }
        return result;
    }

    private Long getMainOfficeIdFromDb(List<MainOfficeEntity> mainOfficeEntityList) {
        if (mainOfficeEntityList.size()==1) {
            return mainOfficeEntityList.get(0).getId();
        }
        return null;
    }


}