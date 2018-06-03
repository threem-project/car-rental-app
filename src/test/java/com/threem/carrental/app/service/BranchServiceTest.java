package com.threem.carrental.app.service;

import com.threem.carrental.app.errorHandler.customExceptions.IncorrectBranchException;
import com.threem.carrental.app.model.dto.AddressBranchDto;
import com.threem.carrental.app.model.dto.BranchDto;
import com.threem.carrental.app.model.entity.BranchEntity;
import com.threem.carrental.app.model.entity.enumTypes.BranchStatusEnum;
import com.threem.carrental.app.repository.BranchRepository;
import com.threem.carrental.app.repository.MainOfficeRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    private BranchRepository branchRepository;

    @Test
    public void shouldCreateAndReturnBranchUsingBranchDto() throws InterruptedException {
        //given
        BranchDto branchDto = BranchDto.builder()
                .status(BranchStatusEnum.OPEN)

                .build();
        AddressBranchDto addressBranchDto = AddressBranchDto.builder()
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

    @Test
    public void shouldFindBranchById() throws Exception{
        //given
        BranchDto branchDto = BranchDto.builder()
                .status(BranchStatusEnum.OPEN)
                .build();
        AddressBranchDto addressBranchDto = AddressBranchDto.builder()
                .city("Warsaw")
                .street("Towarowa")
                .building("20/10")
                .zipCode("02-495")
                .country("Poland")
                .phone("111-222-333")
                .build();
        branchDto.setAddress(addressBranchDto);
        BranchDto createdBranchDto = branchService.createBranch(branchDto);
        //when
        BranchDto branchDtoFromDb = branchService.findBranchById(createdBranchDto.getId());
        //then
        Assertions.assertThat(branchDtoFromDb)
                .hasFieldOrPropertyWithValue("id", createdBranchDto.getId())
                .hasFieldOrPropertyWithValue("status", branchDto.getStatus());
        Assertions.assertThat(branchDtoFromDb.getAddress())
                .hasFieldOrPropertyWithValue("id", branchDtoFromDb.getAddress().getId())
                .hasFieldOrPropertyWithValue("city", addressBranchDto.getCity())
                .hasFieldOrPropertyWithValue("street", addressBranchDto.getStreet())
                .hasFieldOrPropertyWithValue("building", addressBranchDto.getBuilding())
                .hasFieldOrPropertyWithValue("zipCode", addressBranchDto.getZipCode())
                .hasFieldOrPropertyWithValue("country", addressBranchDto.getCountry())
                .hasFieldOrPropertyWithValue("phone", addressBranchDto.getPhone());
    }

    @Test
    public void shouldFindAllBranchesPaginated() {
        //given
        Integer testBranchesNumber = 10;
        for (Integer i=0;i<testBranchesNumber;i++) {
            BranchEntity branchEntity = BranchEntity.builder().status(BranchStatusEnum.OPEN).build();
            branchRepository.save(branchEntity);
        }
        List<BranchEntity> listBeforeChange = branchRepository.findAll();
        Integer sizeBeforeChange = listBeforeChange.size();
        Page<BranchEntity> paginatedBefore = branchService.findAllPaginated(0,sizeBeforeChange);

        //when
        for (Integer i=0;i<testBranchesNumber;i++) {
            BranchEntity branchEntity = BranchEntity.builder().status(BranchStatusEnum.OPEN).build();
            branchRepository.save(branchEntity);
        }
        Page<BranchEntity> paginatedAfter = branchService.findAllPaginated(0,sizeBeforeChange);

        //then
        Assertions.assertThat(paginatedBefore.getTotalPages()).isLessThan(paginatedAfter.getTotalPages());
    }

    @Test
    public void shouldGetListOfAllBranchesFromDb() {
        //given
        Integer listSizeBefore = branchService.findAll().size();
        //when
        branchRepository.save(BranchEntity.builder().status(BranchStatusEnum.OPEN).build());
        Integer listSizeAfter = branchService.findAll().size();
        //then
        Assertions.assertThat(listSizeBefore).isLessThan(listSizeAfter);
    }
}