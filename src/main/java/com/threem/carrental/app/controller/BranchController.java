package com.threem.carrental.app.controller;

import com.threem.carrental.app.model.dto.BranchDto;
import com.threem.carrental.app.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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


    @PostMapping
    ResponseEntity<BranchDto> createBranch(@Validated @RequestBody BranchDto branchDto) {
        Optional<BranchDto> branchDtoFromService = branchService.createBranch(branchDto);
        if (branchDtoFromService.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(branchDtoFromService.get());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //TODO adding branch
    //1.Create BranchDto class
    //2.Create AddressBranchDto class
    //3.Create method in BranchController createBranch(BranchDto fromBranchDto);
    //4.Create BranchMapper class
    //5.Create AddressBranchMapper class
    //6.Create method for mapping AddressBranchDto object to AddressBranchEntity object in AddressBranchMapper
    //7.Create method createBranch(BranchDto fromBranchDto) in BranchService;
    //8.Verification that AddressBranch object can be saved to DB or is invalid in method;
    //9.Save to DB branchEntity object and cascading must be saved AddressBranch object
    //10.Create method findBranchById in BranchService
    //11.Create method for mapping Entities objects to Dto object
    // (think about mapping in BranchMapper and addressBranchMapper or only in BranchMapper)
    //12.Verification in Controller that object was valid saved and found
    //13.Tests to service,mapper(unit),controller(integration)
}
