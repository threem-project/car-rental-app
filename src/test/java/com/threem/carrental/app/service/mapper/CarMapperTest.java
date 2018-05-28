package com.threem.carrental.app.service.mapper;

import com.threem.carrental.app.model.dto.CarDto;
import com.threem.carrental.app.model.entity.BranchEntity;
import com.threem.carrental.app.model.entity.CarEntity;
import com.threem.carrental.app.model.entity.EquipmentEntity;
import com.threem.carrental.app.model.entity.enumTypes.*;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


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
    public void shouldMapCarDtoToCarEntityWhenBranchIdSupplied() {

        //given: CarDto (with branchId)
        List<EquipmentEntity> equipmentEntities = new ArrayList<>();
        BranchEntity dummyBranchEntity = new BranchEntity();
        dummyBranchEntity.setId(123456L);
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

        //then: CarEntity exists with branchid
        Assertions.assertThat(carEntity)
                .hasFieldOrPropertyWithValue("id", 123456L)
                .hasFieldOrPropertyWithValue("vin", "JH2SC2608SM506729")
                .hasFieldOrPropertyWithValue("make", "Ford")
                .hasFieldOrPropertyWithValue("model", "Focus")
                .hasFieldOrPropertyWithValue("bodyType", CarBodyTypeEnum.SEDAN)
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
    public void shouldMapCarDtoToCarEntityWhenNoBranchIdSupplied() {

        //given: CarDto with no branchId
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
                .branchId(null)
                .equipment(equipmentEntities)
//                .photoUrl("https://fakeimageurl.pl")
                .build();

        //when
        CarEntity carEntity = carMapper.toCarEntity(carDto);

        //then
        Assertions.assertThat(carEntity.getBranch().getId() == null);


    }

    @Test
    public void shouldMapCarEntityToCarDto() {

        //given: car in database
        BranchEntity branchEntityFromDbButOnlyWithId = new BranchEntity();
        branchEntityFromDbButOnlyWithId.setId(234567L);

        List<EquipmentEntity> equipmentEntities = new ArrayList<>();
        EquipmentEntity equipmentEntity = new EquipmentEntity();
        equipmentEntity.setId(123L);
        equipmentEntity.setName("entitty");
        equipmentEntities.add(equipmentEntity);

        CarEntity carEntity = new CarEntity().builder()
                .id(99999L)
                .vin("JH2SC2608SM506729")
                .make("Opel")
                .model("Astra")
                .bodyType(CarBodyTypeEnum.ESTATE)
                .year("2015")
                .colour(CarColourEnum.BLUE)
                .mileage(70000)
                .status(CarStatusEnum.IN_REPAIR)
                .dailyRate(new BigDecimal("250.50"))
                .engineType(CarEngineTypeEnum.DIESEL)
                .engineCapacity(2100)
                .segment(CarSegmentTypeEnum.D_LARGE)
                .transmission(CarTransmissionTypeEnum.AUTOMATIC)
                .seats(6)
                .doors(5)
                .branch(branchEntityFromDbButOnlyWithId)
                .equipment(equipmentEntities)
//                .photoUrl("https://fakeurl.pl")
                .build();

        //when: mapping
        CarDto carDto = carMapper.toCarDto(carEntity);

        //then: assertions
        Assertions.assertThat(carDto)
                .hasFieldOrPropertyWithValue("carId", 99999L)
                .hasFieldOrPropertyWithValue("vin", "JH2SC2608SM506729")
                .hasFieldOrPropertyWithValue("make", "Opel")
                .hasFieldOrPropertyWithValue("model", "Astra")
                .hasFieldOrPropertyWithValue("bodyType", CarBodyTypeEnum.ESTATE)
                .hasFieldOrPropertyWithValue("year", "2015")
                .hasFieldOrPropertyWithValue("colour", CarColourEnum.BLUE)
                .hasFieldOrPropertyWithValue("mileage", 70000)
                .hasFieldOrPropertyWithValue("status", CarStatusEnum.IN_REPAIR)
                .hasFieldOrPropertyWithValue("dailyRate", new BigDecimal("250.50"))
                .hasFieldOrPropertyWithValue("engineType", CarEngineTypeEnum.DIESEL)
                .hasFieldOrPropertyWithValue("engineCapacity", 2100)
                .hasFieldOrPropertyWithValue("segment", CarSegmentTypeEnum.D_LARGE)
                .hasFieldOrPropertyWithValue("transmission", CarTransmissionTypeEnum.AUTOMATIC)
                .hasFieldOrPropertyWithValue("seats", 6)
                .hasFieldOrPropertyWithValue("doors", 5)
                .hasFieldOrPropertyWithValue("branchId", 234567L)
                .hasFieldOrPropertyWithValue("equipment", equipmentEntities);
//    .hasFieldOrPropertyWithValue("photoUrl", "https://fakeurl.pl")
    }


}