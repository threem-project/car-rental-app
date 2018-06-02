package com.threem.carrental.app.service;

import com.threem.carrental.app.errorHandler.customExceptions.EmployeeAlreadyExistException;
import com.threem.carrental.app.errorHandler.customExceptions.EmployeeDoesNotExistException;
import com.threem.carrental.app.errorHandler.customExceptions.IncorrectBranchException;
import com.threem.carrental.app.model.dto.EmployeeDto;
import com.threem.carrental.app.model.dto.EmployeeDtoPaginated;
import com.threem.carrental.app.model.entity.BranchEntity;
import com.threem.carrental.app.model.entity.EmployeeEntity;
import com.threem.carrental.app.repository.BranchRepository;
import com.threem.carrental.app.repository.EmployeeRepository;
import com.threem.carrental.app.service.mapper.EmployeeMapper;
import com.threem.carrental.app.utilities.PasswordEncoder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author marek_j on 2018-05-22
 */
@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private BranchRepository branchRepository;
    private EmployeeMapper employeeMapper;
    private PasswordEncoder passwordEncoder;

    public EmployeeService(EmployeeRepository employeeRepository, BranchRepository branchRepository, EmployeeMapper employeeMapper, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.branchRepository = branchRepository;
        this.employeeMapper = employeeMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<EmployeeDto> createEmployee(EmployeeDto givenEmployeeDto) {
        EmployeeEntity employeeEntity = employeeMapper.toEmployeeEntity(givenEmployeeDto);
        EmployeeEntity employeeInDb = findEmployeeInDb(employeeEntity);
        if (employeeInDb != null) {
            throw new EmployeeAlreadyExistException("Given Employee ID is already in DB");
        }
        Optional<EmployeeDto> resultEmployeeDto = createOrUpdateEmployee(givenEmployeeDto, employeeEntity);
        return resultEmployeeDto;
    }

    public Optional<EmployeeDto> updateEmployee(EmployeeDto givenEmployeeDto) {
        EmployeeEntity employeeEntity = employeeMapper.toEmployeeEntity(givenEmployeeDto);
        EmployeeEntity employeeInDb = findEmployeeInDb(employeeEntity);
        if (employeeInDb == null) {
            throw new EmployeeDoesNotExistException("Can't find employee with the given ID");
        }
        Optional<EmployeeDto> resultEmployeeDto = createOrUpdateEmployee(givenEmployeeDto, employeeEntity);
        return resultEmployeeDto;
    }

    public Optional<EmployeeDto> findById(Long id) {
        Optional<EmployeeDto> resultEmployeeDto = Optional.empty();
        Optional<EmployeeEntity> entityFromDb = employeeRepository.findById(id);
        if (entityFromDb.isPresent()) {
            EmployeeDto employeeDto = employeeMapper.toEmployeeDto(entityFromDb.get());
            employeeDto.setPassword(null);
            resultEmployeeDto = Optional.of(employeeDto);
        }
        return resultEmployeeDto;
    }

    @Transactional
    public EmployeeDtoPaginated findAllPaginated(Integer pageNumber, Integer elementsPerPage) {
        PageRequest pageableRequest = PageRequest.of(pageNumber, elementsPerPage);

        Page<EmployeeEntity> employeePage = employeeRepository.findAll(pageableRequest);
        List<EmployeeEntity> employeeEntityList = employeePage.getContent();

        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        employeeEntityList.forEach((e) -> e.getBranch());
        employeeEntityList.forEach((e) -> employeeDtoList.add(employeeMapper.toEmployeeDto(e)));
        employeeDtoList.forEach((e) -> e.setPassword(null));

        EmployeeDtoPaginated employeeDtoPaginated = new EmployeeDtoPaginated().builder()
                .currentPage(pageNumber)
                .elementsPerPage(elementsPerPage)
                .employeeDtoList(employeeDtoList)
                .totalElements(employeePage.getTotalElements())
                .totalPages(employeePage.getTotalPages())
                .build();

        return employeeDtoPaginated;
    }

    private Optional<EmployeeDto> createOrUpdateEmployee(EmployeeDto employeeDto, EmployeeEntity employeeEntity) {
        Optional<EmployeeDto> resultEmployeeDto = Optional.empty();

        if (employeeEntity.getPassword() != null) {
            employeeEntity.setPassword(employeeDto.getPassword());
            employeeEntity = entityWithEncodedPassword(employeeEntity);
        }

        if (employeeEntity.getBranch() != null) {
            employeeEntity = setBranchForEmployee(employeeEntity);
        }

        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
        if (savedEmployeeEntity != null) {
            resultEmployeeDto = employeeDtoWithPasswordNull(savedEmployeeEntity);
        }

        return resultEmployeeDto;
    }

    private EmployeeEntity findEmployeeInDb(EmployeeEntity givenEmployeeEntity) {
        EmployeeEntity employeeEntity = null;
        if (givenEmployeeEntity.getId() != null) {
            Long employeeId = givenEmployeeEntity.getId();
            Optional<EmployeeEntity> employeeEntityOptional = employeeRepository.findById(employeeId);
            if (employeeEntityOptional.isPresent()) {
                employeeEntity = employeeEntityOptional.get();
            }
        }
        return employeeEntity;
    }

    private EmployeeEntity setBranchForEmployee(EmployeeEntity givenEmployee) {
        EmployeeEntity employeeEntity = employeeMapper.toEmployeeEntity(givenEmployee);

        Long employeeBranchId = employeeEntity.getBranch().getId();
        if (employeeBranchId != null) {
            Optional<BranchEntity> branchEntity = branchRepository.findById(employeeBranchId);
            if (branchEntity.isPresent()) {
                employeeEntity.setBranch(branchEntity.get());
            } else {
                throw new IncorrectBranchException("Given branch ID is incorrect");
            }
        } else {
            employeeEntity.setBranch(null);
        }
        return employeeEntity;
    }

    private Optional<EmployeeDto> employeeDtoWithPasswordNull(EmployeeEntity employeeEntity) {
        EmployeeDto employeeDto = employeeMapper.toEmployeeDto(employeeEntity);
        employeeDto.setPassword(null);
        return Optional.of(employeeDto);
    }

    private EmployeeEntity entityWithEncodedPassword(EmployeeEntity employeeEntity) {
        String encodedPassword = passwordEncoder.encode(employeeEntity.getPassword());
        EmployeeEntity updatedEntity = employeeEntity;
        updatedEntity.setPassword(encodedPassword);
        return updatedEntity;
    }
}