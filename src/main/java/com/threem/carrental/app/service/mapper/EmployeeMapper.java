package com.threem.carrental.app.service.mapper;

import com.threem.carrental.app.model.dto.EmployeeDto;
import com.threem.carrental.app.model.entity.EmployeeEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author marek_j on 2018-05-22
 */
@Service
public class EmployeeMapper {
    //todo refactor this part code as soon as branchMappre is available
    public EmployeeEntity ToEmployeeEntity(EmployeeDto fromEmployeeDto) {
        EmployeeEntity employeeEntity = new EmployeeEntity().builder()
                .firstName(fromEmployeeDto.getFirstName())
                .lastName(fromEmployeeDto.getLastName())
                .role(fromEmployeeDto.getRole())
                .status(fromEmployeeDto.getStatus())
                .email(fromEmployeeDto.getEmail())
                .password(fromEmployeeDto.getPassword())
//                .branch(null)
                .build();
        return employeeEntity;
    }

    public EmployeeDto ToEmployeeDto(EmployeeEntity employeeEntity) {
        EmployeeDto employeeDto = new EmployeeDto().builder()
                .firstName(employeeEntity.getFirstName())
                .lastName(employeeEntity.getLastName())
                .role(employeeEntity.getRole())
                .status(employeeEntity.getStatus())
                .email(employeeEntity.getEmail())
                .password(employeeEntity.getPassword())
//                .branchId(employeeEntity.getBranch())
                .build();
        return employeeDto;
    }
}
