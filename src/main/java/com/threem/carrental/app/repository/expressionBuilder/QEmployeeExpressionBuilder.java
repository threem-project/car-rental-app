package com.threem.carrental.app.repository.expressionBuilder;

import com.threem.carrental.app.model.entity.EmployeeEntity;
import com.threem.carrental.app.model.entity.QEmployeeEntity;
import com.querydsl.core.types.dsl.BooleanExpression;

/**
 * @author marek_j on 2018-06-03
 */
public class QEmployeeExpressionBuilder {

    private EmployeeEntity employeeEntity;
    private QEmployeeEntity qEmployeeEntity;
    private BooleanExpression booleanExpression;

    public QEmployeeExpressionBuilder(EmployeeEntity employeeEntity, QEmployeeEntity qEmployeeEntity) {
        this.employeeEntity = employeeEntity;
        this.qEmployeeEntity = qEmployeeEntity;
    }

    public BooleanExpression buildExpression() {
        
    }

    private BooleanExpression buildFristname(BooleanExpression givenExpression) {
        BooleanExpression expression = qEmployeeEntity.employeeEntity.firstName.like(employeeEntity.getFirstName());
        return prepareExpression(givenExpression,expression);
    }

    private BooleanExpression buildLastname(BooleanExpression givenExpression) {
        BooleanExpression expression = qEmployeeEntity.employeeEntity.lastName.like(employeeEntity.getLastName());
        return prepareExpression(givenExpression,expression);
    }

    private BooleanExpression prepareExpression(BooleanExpression givenExpression, BooleanExpression expression) {
        if (givenExpression!=null) {
            expression = expression.and(givenExpression);
        }
        return expression;
    }
}
