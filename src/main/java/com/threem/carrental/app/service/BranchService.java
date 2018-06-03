package com.threem.carrental.app.service;


import com.threem.carrental.app.model.dto.BranchDto;

import com.threem.carrental.app.model.dto.BranchDtoPaginated;
import com.threem.carrental.app.model.entity.AddressBranchEntity;
import com.threem.carrental.app.model.entity.BranchEntity;
import com.threem.carrental.app.service.mapper.AddressBranchMapper;
import com.threem.carrental.app.service.mapper.BranchMapper;
import com.threem.carrental.app.repository.BranchRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author misza_lemko on 22.05.2018
 * @project car-rental-app
 */

@Service
public class BranchService {

    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;
    private final AddressBranchMapper addressBranchMapper;

    @Autowired
    public BranchService(BranchRepository branchRepository, BranchMapper branchMapper, AddressBranchMapper addressBranchMapper) {
        this.branchRepository = branchRepository;
        this.branchMapper = branchMapper;
        this.addressBranchMapper = addressBranchMapper;
    }

    public BranchDto createBranch(BranchDto branchDto) {
        BranchEntity branchEntity = branchMapper.toBranchEntity(branchDto);
        AddressBranchEntity addressBranchEntity = addressBranchMapper.toAddressBranchEntity(branchDto.getAddress());
        branchEntity.setAddress(addressBranchEntity);
        BranchDto resultBranchDto = new BranchDto();

        BranchEntity branchEntityFromDb = branchRepository.save(branchEntity);

        if (branchEntityFromDb.getId() != null) {
            resultBranchDto = branchMapper.toBranchDto(branchEntityFromDb);
            resultBranchDto.setAddress(addressBranchMapper.toAddressBranchDto(branchEntity.getAddress()));
        }
        return resultBranchDto;
    }

    public BranchDto findBranchById(Long id) {
        BranchDto branchDto = new BranchDto();
        Optional<BranchEntity> branchEntityOptional = branchRepository.findById(id);

        if (branchEntityOptional.isPresent()) {
            branchDto = branchMapper.toBranchDto(branchEntityOptional.get());
            AddressBranchEntity addressBranchEntity = branchEntityOptional.get().getAddress();
            branchDto.setAddress(addressBranchMapper.toAddressBranchDto(addressBranchEntity));
        }
        return branchDto;
    }

    public Page<BranchEntity> findAllPaginated(Integer pageNumber, Integer elementsPerPage) {
        PageRequest pageableRequest = PageRequest.of(pageNumber, elementsPerPage);
        return branchRepository.findAll(pageableRequest);
    }

    public List<BranchEntity> findAll() {
        return branchRepository.findAll();
    }
}
