package com.threem.carrental.app.controller;

import com.threem.carrental.app.model.dto.AddressBranchDto;
import com.threem.carrental.app.model.dto.BranchDto;
import com.threem.carrental.app.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author misza_lemko on 22.05.2018
 * @project car-rental-app
 */

@RestController
@RequestMapping("branch")
public class BranchController {

    private final BranchService branchService;

    @Autowired
    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<BranchDto> createBranch(@Validated @RequestBody BranchDto branchDto) {
        BranchDto branchDtoFromService = branchService.createBranch(branchDto);
        if (branchDtoFromService!=null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(branchDtoFromService);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
