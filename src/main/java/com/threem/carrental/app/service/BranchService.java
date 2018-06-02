package com.threem.carrental.app.service;

import com.threem.carrental.app.model.dto.AddressBranchDto;
import com.threem.carrental.app.model.dto.BranchDto;
import com.threem.carrental.app.model.dto.MainOfficeDto;
import com.threem.carrental.app.model.entity.AddressBranchEntity;
import com.threem.carrental.app.model.entity.BranchEntity;
import com.threem.carrental.app.model.entity.MainOfficeEntity;
import com.threem.carrental.app.repository.MainOfficeRepository;
import com.threem.carrental.app.service.mapper.AddressBranchMapper;
import com.threem.carrental.app.service.mapper.BranchMapper;
import com.threem.carrental.app.repository.BranchRepository;
import com.threem.carrental.app.service.mapper.MainOfficeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        BranchEntity branchEntityFromDb = branchRepository.save(branchEntity);

        //todo if branch was not saved branchId will be null.must handle it

        BranchDto resultBranchDto = branchMapper.toBranchDto(branchEntityFromDb);
        resultBranchDto.setAddress(addressBranchMapper.toAddressBranchDto(branchEntity.getAddress()));
        return resultBranchDto;
    }
}
