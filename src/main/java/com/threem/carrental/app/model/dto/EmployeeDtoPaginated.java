package com.threem.carrental.app.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author marek_j on 2018-06-01
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class EmployeeDtoPaginated {

    private Integer currentPage;
    private Integer totalPages;
    private Long totalElements;
    private Integer elementsPerPage;
    private List<EmployeeDto> employeeDtoList;

}
