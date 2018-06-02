package com.threem.carrental.app.service.mapper;

import com.threem.carrental.app.model.dto.BranchDto;
import com.threem.carrental.app.model.entity.BranchEntity;
import com.threem.carrental.app.model.entity.enumTypes.BranchStatusEnum;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

/**
 * @author misza_lemko on 28.05.2018
 * @project car-rental-app
 */
public class BranchMapperTest {

    private BranchMapper branchMapper;

    @Before
    public void setUp() throws Exception {
        branchMapper = new BranchMapper();
    }

    @Test
    public void shouldMapBranchDtoToBranchEntity() throws Exception {
        //given
        BranchDto branchDto = BranchDto.builder()
                .id(1L)
                .status(BranchStatusEnum.OPEN)
                .build();
        //when
        BranchEntity branchEntity = branchMapper.toBranchEntity(branchDto);
        //then
        Assertions.assertThat(branchEntity)
                .hasFieldOrPropertyWithValue("id", branchDto.getId())
                .hasFieldOrPropertyWithValue("status", branchDto.getStatus());
    }

    @Test
    public void shouldMapBranchEntityToBranchDto() throws Exception {
        //given
        BranchEntity branchEntity = BranchEntity.builder()
                .id(1L)
                .status(BranchStatusEnum.OPEN)
                .build();
        //when
        BranchDto branchDto = branchMapper.toBranchDto(branchEntity);
        //then
        Assertions.assertThat(branchDto)
                .hasFieldOrPropertyWithValue("id", branchEntity.getId())
                .hasFieldOrPropertyWithValue("status", branchEntity.getStatus());
    }
}