package com.threem.carrental.app.service.mapper;

import com.threem.carrental.app.model.dto.CarDto;
import com.threem.carrental.app.model.entity.BranchEntity;
import com.threem.carrental.app.model.entity.CarEntity;
import com.threem.carrental.app.model.entity.EquipmentEntity;
import com.threem.carrental.app.model.entity.enumTypes.*;
import io.restassured.internal.assertion.Assertion;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Marika Grzebieniowska on 28.05.2018
 * @project car-rental-app
 */
public class CarMapperTest {

    private CarMapper carMapper;

    @Before
    public void setUp() {
        carMapper = new CarMapper();
    }

    @Test
    public void shouldMapCarDtoToCarEntityWithBranchId() {

        //given: Dto (with branchId)
        List<EquipmentEntity> equipmentEntities = new ArrayList<>();
        CarDto carDto = new CarDto().builder()
                .carId(123456L)
                .vin("JH2SC2608SM506729")
                .make("Ford")
                .model("Focus")
                .bodyType(CarBodyTypeEnum.SEDAN)
                .year("2010")
                .colour(CarColourEnum.WHITE)
                .mileage(280000)
                .status(CarStatusEnum.AVAILABLE)
                .dailyRate(new BigDecimal("500.50"))
                .engineType(CarEngineTypeEnum.PETROL)
                .engineCapacity(1800)
                .segment(CarSegmentTypeEnum.C_MEDIUM)
                .transmission(CarTransmissionTypeEnum.MANUAL)
                .seats(5)
                .doors(4)
                .branchId(123456L)
                .equipment(equipmentEntities)
//                .photoUrl("https://fakeimageurl.pl")
                .build();

        //when: mapping
        CarEntity carEntity = carMapper.toCarEntity(carDto);
        BranchEntity dummyBranchEntity = new BranchEntity();
        dummyBranchEntity.setId(123456L);

        //then: CarEntity exists with branchid
        Assertions.assertThat(carEntity)
        .hasFieldOrPropertyWithValue("id",123456L)
        .hasFieldOrPropertyWithValue("vin","JH2SC2608SM506729")
        .hasFieldOrPropertyWithValue("make","Ford")
        .hasFieldOrPropertyWithValue("model","Focus")
        .hasFieldOrPropertyWithValue("bodyType",CarBodyTypeEnum.SEDAN)
        .hasFieldOrPropertyWithValue("year", "2010")
        .hasFieldOrPropertyWithValue("colour", CarColourEnum.WHITE)
        .hasFieldOrPropertyWithValue("mileage", 280000)
        .hasFieldOrPropertyWithValue("status", CarStatusEnum.AVAILABLE)
        .hasFieldOrPropertyWithValue("dailyRate", new BigDecimal("500.50"))
        .hasFieldOrPropertyWithValue("engineType", CarEngineTypeEnum.PETROL)
        .hasFieldOrPropertyWithValue("engineCapacity", 1800)
        .hasFieldOrPropertyWithValue("segment", CarSegmentTypeEnum.C_MEDIUM)
        .hasFieldOrPropertyWithValue("transmission", CarTransmissionTypeEnum.MANUAL)
        .hasFieldOrPropertyWithValue("seats", 5)
        .hasFieldOrPropertyWithValue("doors", 4)
        .hasFieldOrPropertyWithValue("equipment", equipmentEntities);

        Assertions.assertThat(carEntity.getBranch().equals(dummyBranchEntity));
    }

    @Test
    public void shouldMapCarDtoToCarEntityWithNoBranchId() {

        //given

        //when

        //then
    }

    @Test
    public void shouldMapCarEntityToCarDtoBranch() {

        //given

        //when

        //then

    }

    @Test
    public void shouldMapCarEntityToCarDtoWithNoBranch() {

        //given

        //when

        //then

    }

}