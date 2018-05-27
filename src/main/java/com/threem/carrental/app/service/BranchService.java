package com.threem.carrental.app.service;

import com.threem.carrental.app.model.dto.BranchDto;
import com.threem.carrental.app.model.entity.AddressBranchEntity;
import com.threem.carrental.app.model.entity.BranchEntity;
import com.threem.carrental.app.model.entity.MainOfficeEntity;
import com.threem.carrental.app.service.mapper.AddressBranchMapper;
import com.threem.carrental.app.service.mapper.BranchMapper;
import com.threem.carrental.app.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Optional<BranchDto> createBranch(BranchDto branchDto) {
        Optional<BranchDto> resultBranchDto = Optional.empty();
        BranchEntity branchEntity = branchMapper.toBranchEntity(branchDto);
        AddressBranchEntity addressBranchEntity = addressBranchMapper.toAddressBranchEntity(branchDto.getAddress());
        branchEntity.setAddress(addressBranchEntity);

        branchRepository.save(branchEntity);

        Optional<BranchEntity> branchEntityFromDb = branchRepository.findById(branchEntity.getId());
        AddressBranchEntity addressBranchEntityFromDb = branchEntityFromDb.get().getAddress();
        MainOfficeEntity mainOfficeEntityFromDb = branchEntityFromDb.get().getMainOffice();
        if (branchEntityFromDb.isPresent()) {
            resultBranchDto = Optional.of(branchMapper.toBranchDto(branchEntityFromDb.get()));
            resultBranchDto.get().setAddress(addressBranchMapper.toAddressBranchDto(addressBranchEntityFromDb));
            //todo
            // resultBranchDto.get().setMainOffice(mainOfficeEntityFromDb);
        }
        return resultBranchDto;
    }


}
