package com.threem.carrental.app.repository.expressionBuilder;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.threem.carrental.app.model.entity.AddressBranchEntity;
import com.threem.carrental.app.model.entity.QEmployeeEntity;
import com.threem.carrental.app.model.entity.enumTypes.EmployeeRoleEnum;
import com.threem.carrental.app.model.entity.enumTypes.EmployeeStatusEnum;
import org.springframework.util.StringUtils;

/**
 * @author marek_j on 2018-06-03
 */
public class QEmployeeExpressionBuilder {

    private BooleanExpression booleanExpression;
    private Boolean hasExpression;

    public QEmployeeExpressionBuilder(Builder builder) {
        this.booleanExpression = builder.booleanExpression;
        this.hasExpression = builder.hasExpression;
    }

    public BooleanExpression getExpression() {
        return booleanExpression;
    }

    public Boolean hasExpression() {
        return hasExpression;
    }

    public static class Builder {

        private BooleanExpression booleanExpression;
        private Boolean hasExpression=false;

        public Builder id(Long id) {
            if (id!=null) {
                BooleanExpression expression = QEmployeeEntity.employeeEntity.id.eq(id);
                this.booleanExpression = initOrUpdateExpression(booleanExpression,expression);
                this.hasExpression = true;
            }
            return this;
        }

        public Builder firstName(String firstName) {
            if (!StringUtils.isEmpty(firstName)) {
                BooleanExpression expression = QEmployeeEntity.employeeEntity.firstName.like(firstName);
                this.booleanExpression = initOrUpdateExpression(booleanExpression,expression);
                this.hasExpression = true;
            }
            return this;
        }

        public Builder lastName(String lastName) {
            if (!StringUtils.isEmpty(lastName)) {
                BooleanExpression expression = QEmployeeEntity.employeeEntity.lastName.like(lastName);
                this.booleanExpression = initOrUpdateExpression(booleanExpression, expression);
                this.hasExpression = true;
            }
            return this;
        }

        public Builder role(EmployeeRoleEnum role) {
            if (role!=null) {
                BooleanExpression expression = QEmployeeEntity.employeeEntity.role.eq(role);
                this.booleanExpression = initOrUpdateExpression(booleanExpression, expression);
                this.hasExpression = true;
            }
            return this;
        }

        public Builder status(EmployeeStatusEnum status) {
            if (status!=null) {
                BooleanExpression expression = QEmployeeEntity.employeeEntity.status.eq(status);
                this.booleanExpression = initOrUpdateExpression(booleanExpression, expression);
                this.hasExpression = true;
            }
            return this;
        }

        public Builder email(String email) {
            if (!StringUtils.isEmpty(email)) {
                BooleanExpression expression = QEmployeeEntity.employeeEntity.email.like(email);
                this.booleanExpression = initOrUpdateExpression(booleanExpression,expression);
                this.hasExpression = true;
            }
            return this;
        }

        public Builder addressCity(AddressBranchEntity addressBranchEntity) {
            if (addressBranchEntity!=null || !StringUtils.isEmpty(addressBranchEntity.getCity())) {
                String city = addressBranchEntity.getCity();
                BooleanExpression expression = QEmployeeEntity.employeeEntity.branch.address.city.like(city);
                this.booleanExpression = initOrUpdateExpression(booleanExpression,expression);
                this.hasExpression = true;
            }
            return this;
        }

        public QEmployeeExpressionBuilder build() {
            return new QEmployeeExpressionBuilder(this);
        }

        private BooleanExpression initOrUpdateExpression(BooleanExpression originalExpr, BooleanExpression newExpr) {
            BooleanExpression resultExpression = null;
            if (originalExpr!=null) {
                resultExpression = originalExpr.and(newExpr);
            } else {
                resultExpression = newExpr;
            }
            return resultExpression;
        }
    }
}
