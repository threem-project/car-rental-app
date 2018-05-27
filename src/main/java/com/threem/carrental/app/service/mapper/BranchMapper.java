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

@Service
public class BranchMapper {

    public BranchEntity toBranchEntity(BranchDto fromBranchDto) {
        AddressBranchEntity addressBranchEntity = AddressBranchEntity.builder()
                .id(fromBranchDto.getAddress().getId())
                .build();
        MainOfficeEntity mainOfficeEntity = MainOfficeEntity.builder()
                .id(fromBranchDto.getMainOffice().getId())
                .build();


        return BranchEntity.builder()
                .id(fromBranchDto.getId())
                .address(addressBranchEntity)
                .mainOffice(mainOfficeEntity)
                //.bookings()
                .employees(fromBranchDto.getEmployees())
                //.cars()
                .status(fromBranchDto.getBranchStatus())
                .build();
    }
}
