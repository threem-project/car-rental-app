package com.threem.carrental.app.controller;

import com.threem.carrental.app.model.dto.BranchDto;
import com.threem.carrental.app.model.entity.BranchEntity;
import com.threem.carrental.app.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
        if (branchDtoFromService != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(branchDtoFromService);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<BranchDto> findBranchById(@PathVariable Long id) {
        BranchDto branchDto = branchService.findBranchById(id);
        if (branchDto.getId() == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(branchDto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<Page<BranchEntity>> findAllPaginated(@RequestParam(defaultValue = "0") Integer currentPage,
                                                        @RequestParam(defaultValue = "20") Integer resultsPerPage) {
        Page<BranchEntity> branchEntityPage = branchService.findAllPaginated(currentPage, resultsPerPage);
            return ResponseEntity.ok(branchEntityPage);
    }

    @GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<List<BranchEntity>> findAll() {
        List<BranchEntity> branchEntities = branchService.findAll();
        return ResponseEntity.ok(branchEntities);
    }
}

