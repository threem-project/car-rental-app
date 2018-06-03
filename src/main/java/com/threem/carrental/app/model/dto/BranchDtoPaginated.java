package com.threem.carrental.app.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author misza_lemko on 02.06.2018
 * @project car-rental-app
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class BranchDtoPaginated {

    private Integer currentPage;
    private Integer totalPages;
    private Long totalElements;
    private Integer elementsPerPage;
    private List<BranchDto> branchDtoList;
}
