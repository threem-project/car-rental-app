package com.threem.carrental.app.service.mapper;

import com.threem.carrental.app.model.dto.AddressBranchDto;
import com.threem.carrental.app.model.entity.AddressBranchEntity;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author misza_lemko on 27.05.2018
 * @project car-rental-app
 */
public class AddressBranchMapperTest {

    private AddressBranchMapper addressBranchMapper;

    @Before
    public void setup() throws Exception {
        addressBranchMapper = new AddressBranchMapper();
    }

    @Test
    public void shouldMapAddressBranchDtoToAddressBranchEntity() throws Exception {
        //given
        AddressBranchDto addressBranchDto = AddressBranchDto.builder()
                .id(1L)
                .city("Warsaw")
                .street("Towarowa")
                .building("20/10")
                .zipCode("02-495")
                .country("Poland")
                .phone("111-222-333")
                .build();
        //when
        AddressBranchEntity addressBranchEntity = addressBranchMapper.toAddressBranchEntity(addressBranchDto);
        //then
        Assertions.assertThat(addressBranchEntity)
                .hasFieldOrPropertyWithValue("id", addressBranchDto.getId())
                .hasFieldOrPropertyWithValue("city", addressBranchDto.getCity())
                .hasFieldOrPropertyWithValue("street", addressBranchDto.getStreet())
                .hasFieldOrPropertyWithValue("building", addressBranchDto.getBuilding())
                .hasFieldOrPropertyWithValue("zipCode", addressBranchDto.getZipCode())
                .hasFieldOrPropertyWithValue("country", addressBranchDto.getCountry())
                .hasFieldOrPropertyWithValue("phone", addressBranchDto.getPhone());
    }

    @Test
    public void shouldMapAddressBranchEntityToAddressBranchDto() throws Exception {
        //given
        AddressBranchEntity addressBranchEntity = AddressBranchEntity.builder()
                .id(1L)
                .city("Warsaw")
                .street("Towarowa")
                .building("20/10")
                .zipCode("02-495")
                .country("Poland")
                .phone("111-222-333")
                .build();
        //when
        AddressBranchDto addressBranchDto = addressBranchMapper.toAddressBranchDto(addressBranchEntity);
        //then
        Assertions.assertThat(addressBranchDto)
                .hasFieldOrPropertyWithValue("id", addressBranchEntity.getId())
                .hasFieldOrPropertyWithValue("city", addressBranchEntity.getCity())
                .hasFieldOrPropertyWithValue("street", addressBranchEntity.getStreet())
                .hasFieldOrPropertyWithValue("building", addressBranchEntity.getBuilding())
                .hasFieldOrPropertyWithValue("zipCode", addressBranchEntity.getZipCode())
                .hasFieldOrPropertyWithValue("country", addressBranchEntity.getCountry())
                .hasFieldOrPropertyWithValue("phone", addressBranchEntity.getPhone());
    }
}