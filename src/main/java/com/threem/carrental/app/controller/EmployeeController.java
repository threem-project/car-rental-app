package com.threem.carrental.app.controller;

import com.threem.carrental.app.model.dto.EmployeeDto;
import com.threem.carrental.app.service.EmployeeService;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author marek_j on 2018-05-22
 */
@RestController
@RequestMapping("employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@Validated @RequestBody EmployeeDto employeeDto) {
        Optional<EmployeeDto> employeeDtoFromService = employeeService.createEmployee(employeeDto);
        if (employeeDtoFromService.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(employeeDtoFromService.get());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping
    public ResponseEntity<EmployeeDto> editEmployee(@Validated @RequestBody EmployeeDto employeeDto) {
        throw new NotYetImplementedException("this is not yet implemented....");
    }
}
