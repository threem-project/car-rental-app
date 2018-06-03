package com.threem.carrental.app.model.dto;

import com.threem.carrental.app.model.entity.EmployeeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author marek_j on 2018-06-03
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class FindEmployeeDto {

    EmployeeEntity employeeEntity;

}
