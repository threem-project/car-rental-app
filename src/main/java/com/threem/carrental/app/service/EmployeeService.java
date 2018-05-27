package com.threem.carrental.app.service;

import com.threem.carrental.app.errorHandler.customExceptions.IncorrectBranchException;
import com.threem.carrental.app.model.dto.EmployeeDto;
import com.threem.carrental.app.model.entity.BranchEntity;
import com.threem.carrental.app.model.entity.EmployeeEntity;
import com.threem.carrental.app.repository.BranchRepository;
import com.threem.carrental.app.repository.EmployeeRepository;
import com.threem.carrental.app.service.mapper.EmployeeMapper;
import com.threem.carrental.app.utilities.PasswordEncoder;
import org.springframework.stereotype.Service;

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


    public Optional<EmployeeDto> createEmployee(EmployeeDto employeeDto) {
        Optional<EmployeeDto> resultEmployeeDto = Optional.empty();
        EmployeeEntity employeeEntity = employeeMapper.toEmployeeEntity(employeeDto);
        String encodedPassword = passwordEncoder.encode(employeeEntity.getPassword());
        employeeEntity.setPassword(encodedPassword);

        Long employeeBranchId = employeeEntity.getBranch().getId();
        if (employeeEntity.getBranch()!=null && employeeBranchId!=null) {
            Optional<BranchEntity> branchEntity = branchRepository.findById(employeeBranchId);
            if (branchEntity.isPresent()) {
                employeeEntity.setBranch(branchEntity.get());
            } else {
                throw new IncorrectBranchException("Given branch ID is incorrect");
            }
        }

        employeeRepository.save(employeeEntity);

        Optional<EmployeeEntity> employeeEntityFromDb = employeeRepository.findById(employeeEntity.getId());
        if (employeeEntityFromDb.isPresent()) {
            resultEmployeeDto = Optional.of(employeeMapper.toEmployeeDto(employeeEntityFromDb.get()));
            resultEmployeeDto.get().setPassword(null);
        }
        return resultEmployeeDto;
    }

}
