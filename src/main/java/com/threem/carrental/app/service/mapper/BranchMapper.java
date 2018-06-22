package com.threem.carrental.app.service.mapper;

import com.threem.carrental.app.model.dto.BranchDto;
import com.threem.carrental.app.model.entity.AddressBranchEntity;
import com.threem.carrental.app.model.entity.BookingEntity;
import com.threem.carrental.app.model.entity.BranchEntity;
import com.threem.carrental.app.model.entity.MainOfficeEntity;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author misza_lemko on 22.05.2018
 * @project car-rental-app
 */
@Deprecated
@Service
public class BranchMapper {

    public BranchEntity toBranchEntity(BranchDto fromBranchDto) {
        return BranchEntity.builder()
                .id(fromBranchDto.getId())
                .status(fromBranchDto.getStatus())
                .build();
    }

    public BranchDto toBranchDto(BranchEntity fromBranchEntity) {
        return BranchDto.builder()
                .id(fromBranchEntity.getId())
                .status(fromBranchEntity.getStatus())
                .build();
    }
}
