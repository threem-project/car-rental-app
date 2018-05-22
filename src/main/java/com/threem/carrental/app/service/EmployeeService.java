package com.threem.carrental.app.service;

import com.threem.carrental.app.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

/**
 * @author marek_j on 2018-05-22
 */
@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


}
