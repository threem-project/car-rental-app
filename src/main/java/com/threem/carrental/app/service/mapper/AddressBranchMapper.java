package com.threem.carrental.app.service.mapper;

import com.threem.carrental.app.model.dto.AddressBranchDto;
import com.threem.carrental.app.model.entity.AddressBranchEntity;
import org.springframework.stereotype.Service;

/**
 * @author misza_lemko on 25.05.2018
 * @project car-rental-app
 */

@Service
public class AddressBranchMapper {

    public AddressBranchEntity toAddressBranchEntity(AddressBranchDto fromAddressBranchDto) {
        return AddressBranchEntity.builder()
                .id(fromAddressBranchDto.getId())
                .city(fromAddressBranchDto.getCity())
                .street(fromAddressBranchDto.getStreet())
                .building(fromAddressBranchDto.getBuilding())
                .zipCode(fromAddressBranchDto.getZipCode())
                .country(fromAddressBranchDto.getCountry())
                .phone(fromAddressBranchDto.getPhone())
                .build();
    }

    public AddressBranchDto toAddressBranchDto(AddressBranchEntity fromAddressBranchEntity) {
        return AddressBranchDto.builder()
                .id(fromAddressBranchEntity.getId())
                .city(fromAddressBranchEntity.getCity())
                .street(fromAddressBranchEntity.getStreet())
                .building(fromAddressBranchEntity.getBuilding())
                .zipCode(fromAddressBranchEntity.getZipCode())
                .country(fromAddressBranchEntity.getCountry())
                .phone(fromAddressBranchEntity.getPhone())
                .build();
    }
}
