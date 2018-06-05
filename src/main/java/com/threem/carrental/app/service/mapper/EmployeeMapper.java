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

//    public EmployeeEntity toEmployeeEntity(EmployeeEntity fromEmployeeEntity) {
//        EmployeeEntity employeeEntity = new EmployeeEntity().builder()
//                .id(fromEmployeeEntity.getId())
//                .firstName(fromEmployeeEntity.getFirstName())
//                .lastName(fromEmployeeEntity.getLastName())
//                .email(fromEmployeeEntity.getEmail())
//                .password(fromEmployeeEntity.getPassword())
//                .status(fromEmployeeEntity.getStatus())
//                .role(fromEmployeeEntity.getRole())
//                .branch(fromEmployeeEntity.getBranch())
//                .build();
//        return employeeEntity;
//    }
//
//    public EmployeeEntity toEmployeeEntity(EmployeeDto fromEmployeeDto) {
//        BranchEntity branchEntity = new BranchEntity();
//        if (fromEmployeeDto.getBranchId()!=null) {
//            branchEntity.setId(fromEmployeeDto.getBranchId());
//        }
//
//        EmployeeEntity employeeEntity = new EmployeeEntity().builder()
//                .id(fromEmployeeDto.getEmployeeId())
//                .firstName(fromEmployeeDto.getFirstName())
//                .lastName(fromEmployeeDto.getLastName())
//                .role(fromEmployeeDto.getRole())
//                .status(fromEmployeeDto.getStatus())
//                .email(fromEmployeeDto.getEmail())
//                .password(fromEmployeeDto.getPassword())
//                .branch(branchEntity)
//                .build();
//        return employeeEntity;
//    }
//
//    public EmployeeDto toEmployeeDto(EmployeeEntity fromEmployeeEntity) {
//        Long branchId = null;
//        if (fromEmployeeEntity.getBranch()!=null) {
//            branchId = fromEmployeeEntity.getBranch().getId();
//        }
//
//        EmployeeDto employeeDto = new EmployeeDto().builder()
//                .employeeId(fromEmployeeEntity.getId())
//                .firstName(fromEmployeeEntity.getFirstName())
//                .lastName(fromEmployeeEntity.getLastName())
//                .role(fromEmployeeEntity.getRole())
//                .status(fromEmployeeEntity.getStatus())
//                .email(fromEmployeeEntity.getEmail())
//                .password(fromEmployeeEntity.getPassword())
//                .branchId(branchId)
//                .build();
//        return employeeDto;
//    }
}
