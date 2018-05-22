package com.threem.carrental.app.controller;

import com.threem.carrental.app.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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




    //TODO adding branch
    //1.Create BranchDto class
    //2.Create AddressBranchDto class
    //3.Create method in BranchController createBranch(BranchDto fromBranchDto);
    //4.Create BranchMapper class
    //5.Create AddressBranchMapper
    //6.Create method for mapping AddressBranchDto object to AddressBranchEntity in AddressBranchMapper
    //7.Veryfication that object can be saved to DB o is invalid
    //8.Create method createBranch(BranchDto fromBranchDto) in BranchService;
}
