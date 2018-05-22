package com.threem.carrental.app.controller;

import com.threem.carrental.app.model.dto.EmployeeDto;
import com.threem.carrental.app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    //todo dodowania employee
    //1. utworzyć klasę EmployeeDto
    //2. tworzymy metodę createEmployee, która przyjmie jako parametr EmployeeDto
    //3. tworzymy mapper i metodę do mapowania EmployeeDto na EmployeeEntity
    //4. tworzymy w service metodę createEmployee, która przyjmuje EmployeeDto
    //5.

    @PostMapping
    public ResponseEntity<?> createEmployee(@Validated @RequestBody EmployeeDto employeeDto) {

        return null;
    }

}
