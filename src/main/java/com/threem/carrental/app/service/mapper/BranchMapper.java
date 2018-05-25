package com.threem.carrental.app.service.mapper;

import com.threem.carrental.app.model.dto.AddressBranchDto;
import com.threem.carrental.app.model.dto.BranchDto;
import com.threem.carrental.app.model.entity.BranchEntity;
import org.springframework.stereotype.Service;

/**
 * @author misza_lemko on 22.05.2018
 * @project car-rental-app
 */

@Service
public class BranchMapper {

    private final AddressBranchMapper addressBranchMapper;

    public BranchMapper(AddressBranchMapper addressBranchMapper) {
        this.addressBranchMapper = addressBranchMapper;
    }

    public BranchEntity toBranchEntity(BranchDto fromBranchDto, AddressBranchDto fromAddressBranchDto) {
        return BranchEntity.builder()
                .address(addressBranchMapper.toAddressBranchEntity(fromAddressBranchDto))
                .build();
    }
}
