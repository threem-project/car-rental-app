package com.threem.carrental.app.service.mapper;

import com.threem.carrental.app.model.dto.MainOfficeDto;
import com.threem.carrental.app.model.entity.MainOfficeEntity;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author misza_lemko on 28.05.2018
 * @project car-rental-app
 */
public class MainOfficeMapperTest {

    private MainOfficeMapper mainOfficeMapper;

    @Before
    public void setUp() throws Exception {
        mainOfficeMapper = new MainOfficeMapper();
    }

    @Test
    public void shouldMapMainOfficeDtoToMainOfficeEntity() throws Exception {
        //given
        MainOfficeDto mainOfficeDto = MainOfficeDto.builder()
                .id(1L)
                .name("Google")
                .domain("google.com")
                .address("Mountain View, California, U.S.")
                .phone("111-222-333")
                .email("office@google.com")
                .build();
        //when
        MainOfficeEntity mainOfficeEntity = mainOfficeMapper.toMainOfficeEntity(mainOfficeDto);
        //then
        Assertions.assertThat(mainOfficeEntity)
                .hasFieldOrPropertyWithValue("id", mainOfficeDto.getId())
                .hasFieldOrPropertyWithValue("name", mainOfficeDto.getName())
                .hasFieldOrPropertyWithValue("domain", mainOfficeDto.getDomain())
                .hasFieldOrPropertyWithValue("address", mainOfficeDto.getAddress())
                .hasFieldOrPropertyWithValue("phone", mainOfficeDto.getPhone())
                .hasFieldOrPropertyWithValue("email", mainOfficeDto.getEmail());
    }

    @Test
    public void shouldMapMainOfficeEntityToMainOfficeDto() throws Exception {
        //given
        MainOfficeEntity mainOfficeEntity = MainOfficeEntity.builder()
                .id(1L)
                .name("Google")
                .domain("google.com")
                .address("Mountain View, California, U.S.")
                .phone("111-222-333")
                .email("office@google.com")
                .build();
        //when
        MainOfficeDto mainOfficeDto = mainOfficeMapper.toMainOfficeDto(mainOfficeEntity);
        //then
        Assertions.assertThat(mainOfficeDto)
                .hasFieldOrPropertyWithValue("id", mainOfficeEntity.getId())
                .hasFieldOrPropertyWithValue("name", mainOfficeEntity.getName())
                .hasFieldOrPropertyWithValue("domain", mainOfficeEntity.getDomain())
                .hasFieldOrPropertyWithValue("address", mainOfficeEntity.getAddress())
                .hasFieldOrPropertyWithValue("phone", mainOfficeEntity.getPhone())
                .hasFieldOrPropertyWithValue("email", mainOfficeEntity.getEmail());
    }
}