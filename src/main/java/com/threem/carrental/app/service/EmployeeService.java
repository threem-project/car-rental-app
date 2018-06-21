package com.threem.carrental.app.service;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.threem.carrental.app.errorHandler.customExceptions.EmployeeAlreadyExistException;
import com.threem.carrental.app.errorHandler.customExceptions.EmployeeDoesNotExistException;
import com.threem.carrental.app.model.entity.EmployeeEntity;
import com.threem.carrental.app.repository.EmployeeRepository;
import com.threem.carrental.app.repository.expressionBuilder.QEmployeeExpressionBuilder;
import com.threem.carrental.app.utilities.PasswordEncoder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author marek_j on 2018-05-22
 */
@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Optional<EmployeeEntity> createEmployee(EmployeeEntity givenEntity) {
        if (entityExistInDb(givenEntity)) {
            throw new EmployeeAlreadyExistException("Given Employee ID is already in DB");
        }
        givenEntity.setPassword(PasswordEncoder.encode(givenEntity.getPassword()));
        employeeRepository.save(givenEntity);
        return Optional.of(entityWithNullPassword(givenEntity));
    }

    public Optional<EmployeeEntity> updateEmployee(EmployeeEntity givenEntity) {
        if (!entityExistInDb(givenEntity)) {
            throw new EmployeeDoesNotExistException("Can't find employee with the given ID");
        }
        givenEntity.setPassword(PasswordEncoder.encode(givenEntity.getPassword()));
        employeeRepository.save(givenEntity);
        return Optional.of(entityWithNullPassword(givenEntity));
    }

    public Optional<EmployeeEntity> findById(Long id) {
        Optional<EmployeeEntity> resultEntity = employeeRepository.findById(id);
        if (resultEntity.isPresent()){
            resultEntity.get().setPassword(null);
        }
        return resultEntity;
    }

    public Page<EmployeeEntity> findAllPaginated(Integer pageNumber, Integer elementsPerPage) {
        PageRequest pageableRequest = PageRequest.of(pageNumber, elementsPerPage);
        Page<EmployeeEntity> employeePage = employeeRepository.findAll(pageableRequest);
        employeePage.getContent().forEach(e -> e.setPassword(null));
        return employeePage;
    }

    public List<EmployeeEntity> findByEmployeeEntity(EmployeeEntity employeeEntity) {
        List<EmployeeEntity> employeesList = new ArrayList<>();

        QEmployeeExpressionBuilder builder = new QEmployeeExpressionBuilder.Builder()
                .id(employeeEntity.getId())
                .firstName(employeeEntity.getFirstName())
                .lastName(employeeEntity.getLastName())
                .email(employeeEntity.getEmail())
                .role(employeeEntity.getRole())
                .status(employeeEntity.getStatus())
                .addressCity(employeeEntity.getBranch().getAddress())
                .build();

        if (builder.hasExpression()) {
            BooleanExpression booleanExpression = builder.getExpression();
            Iterable<EmployeeEntity> employeeEntities = employeeRepository.findAll(booleanExpression);
            employeeEntities.forEach(e -> employeesList.add(e));
        }

        return employeesList;
    }

    private EmployeeEntity entityWithNullPassword(EmployeeEntity employeeEntity) {
        EmployeeEntity entity = employeeEntity;
        entity.setPassword(null);
        return entity;
    }

    private Boolean entityExistInDb(EmployeeEntity givenEntity) {
        Boolean result = false;
        if (givenEntity.getId()!=null) {
            Optional<EmployeeEntity> employeeInDb = employeeRepository.findById(givenEntity.getId());
            if (employeeInDb.isPresent()) {
                result = true;
            }
        }
        return result;
    }
}