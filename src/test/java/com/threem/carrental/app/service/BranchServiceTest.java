package com.threem.carrental.app.service;

import com.threem.carrental.app.model.dto.AddressBranchDto;
import com.threem.carrental.app.model.dto.BranchDto;
import com.threem.carrental.app.model.entity.MainOfficeEntity;
import com.threem.carrental.app.model.entity.enumTypes.BranchStatusEnum;
import com.threem.carrental.app.repository.MainOfficeRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author misza_lemko on 28.05.2018
 * @project car-rental-app
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Rollback
@Transactional
@ActiveProfiles("test")
public class BranchServiceTest {

    @Autowired
    private BranchService branchService;
    @Autowired
    private MainOfficeRepository mainOfficeRepository;

    @Test
    public void shouldCreateAndReturnBranchUsingBranchDto() throws InterruptedException {
        //given
        BranchDto branchDto = BranchDto.builder()
                .id(null)
                .status(BranchStatusEnum.OPENED)
                .build();
        AddressBranchDto addressBranchDto = AddressBranchDto.builder()
                .id(null)
                .city("Warsaw")
                .street("Towarowa")
                .building("20/10")
                .zipCode("02-495")
                .country("Poland")
                .phone("111-222-333")
                .build();
        branchDto.setAddress(addressBranchDto);
        //when
        BranchDto createdBranch = branchService.createBranch(branchDto);
        //then
        Assertions.assertThat(createdBranch)
                .hasFieldOrPropertyWithValue("id", createdBranch.getId())
                .hasFieldOrPropertyWithValue("status", branchDto.getStatus());
        Assertions.assertThat(createdBranch.getAddress())
                .hasFieldOrPropertyWithValue("id", createdBranch.getAddress().getId())
                .hasFieldOrPropertyWithValue("city", addressBranchDto.getCity())
                .hasFieldOrPropertyWithValue("street", addressBranchDto.getStreet())
                .hasFieldOrPropertyWithValue("building", addressBranchDto.getBuilding())
                .hasFieldOrPropertyWithValue("zipCode", addressBranchDto.getZipCode())
                .hasFieldOrPropertyWithValue("country", addressBranchDto.getCountry())
                .hasFieldOrPropertyWithValue("phone", addressBranchDto.getPhone());
    }
}