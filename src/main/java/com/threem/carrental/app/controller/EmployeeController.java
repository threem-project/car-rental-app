package com.threem.carrental.app.controller;

import com.threem.carrental.app.model.dto.EmployeeDto;
import com.threem.carrental.app.model.entity.EmployeeEntity;
import com.threem.carrental.app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @PostMapping (produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<EmployeeDto> createEmployee(@Validated @RequestBody EmployeeDto employeeDto) {
        Optional<EmployeeDto> employeeDtoFromService = employeeService.createEmployee(employeeDto);
        if (employeeDtoFromService.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(employeeDtoFromService.get());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping (produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<EmployeeDto> editEmployee(@Validated @RequestBody EmployeeDto employeeDto) {
        Optional<EmployeeDto> employeeDtoFromService = employeeService.updateEmployee(employeeDto);
        if (employeeDtoFromService.isPresent()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeeDtoFromService.get());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping (value = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<EmployeeDto> findById(@PathVariable Long id) {
        Optional<EmployeeDto> employeeDtoFromService = employeeService.findById(id);
        if (employeeDtoFromService.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(employeeDtoFromService.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping (produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Page<EmployeeEntity>> findAllPaginated(@RequestParam(defaultValue = "0") Integer currentPage,
                                                                 @RequestParam(defaultValue = "20") Integer resultsPerPage) {
        Page<EmployeeEntity> employeeEntityPage = employeeService.findAllPaginated(currentPage,resultsPerPage);
        return ResponseEntity.status(HttpStatus.OK).body(employeeEntityPage);
    }
}
