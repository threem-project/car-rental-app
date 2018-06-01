package com.threem.carrental.app.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

/**
 * @author marek_j on 2018-06-01
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class EmployeeDtoPaginated {

    private Integer currentIndex;
    private Integer firstIndex;
    private Integer lastIndex;
    private Integer elementsPerPage;
    private Page<EmployeeDto> employeeDtoPage;

}
