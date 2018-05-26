package com.threem.carrental.app.service.mapper;

import com.threem.carrental.app.model.dto.EmployeeDto;
import com.threem.carrental.app.model.entity.BranchEntity;
import com.threem.carrental.app.model.entity.EmployeeEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author marek_j on 2018-05-22
 */
@Service
public class EmployeeMapper {
    //todo refactor this part code as soon as branchMappre is available
    public EmployeeEntity toEmployeeEntity(EmployeeDto fromEmployeeDto) {
        EmployeeEntity employeeEntity = new EmployeeEntity().builder()
                .id(fromEmployeeDto.getEmployeeId())
                .firstName(fromEmployeeDto.getFirstName())
                .lastName(fromEmployeeDto.getLastName())
                .role(fromEmployeeDto.getRole())
                .status(fromEmployeeDto.getStatus())
                .email(fromEmployeeDto.getEmail())
                .password(fromEmployeeDto.getPassword())
//                .branch() //todo ustawić tutaj dummyBrnach
//                .bookings() //todo ustawić tutaj bookings
                .build();
        return employeeEntity;
    }

    public EmployeeDto toEmployeeDto(EmployeeEntity employeeEntity) {
        EmployeeDto employeeDto = new EmployeeDto().builder()
                .employeeId(employeeEntity.getId())
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
