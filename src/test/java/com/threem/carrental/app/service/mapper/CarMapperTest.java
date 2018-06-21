package com.threem.carrental.app.service.mapper;

import com.threem.carrental.app.model.dto.CarDto;
import com.threem.carrental.app.model.entity.BranchEntity;
import com.threem.carrental.app.model.entity.CarEntity;
import com.threem.carrental.app.model.entity.EquipmentEntity;
import com.threem.carrental.app.model.entity.enumTypes.*;
import com.threem.carrental.app.repository.EquipmentRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

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

//    @Test
//    public void shouldMapCarDtoToCarEntityWhenBranchIdSuppliedAndEquipmentListFilled() { // adding new car
//        //given: CarDto with branchId, filled equipment list
//        CarDto carDto = buildCarDto(3L, true);
//        //when: mapping
//        CarEntity carEntity = carMapper.toCarEntity(carDto);
//        //then: CarEntity exists with branchId
//        checkCarEntityFieldVsCarDtoFields(carEntity, carDto);
//        Assertions.assertThat(carEntity.getEquipment().size() == 3);
//        Assertions.assertThat(carEntity.getEquipment().get(2).getName().equals(carDto.getEquipment().get(2).getName()));
//        Assertions.assertThat(carEntity.getBranch().getId().equals(carDto.getBranchId()));
//        Assertions.assertThat(carEntity.getEquipment().equals(carDto.getEquipment()));
//    }
//
//    @Test
//    public void shouldMapCarDtoToCarEntityWhenBranchIdSuppliedAndEquipmentListNull() { // adding new car with no equipment
//
//        //given: CarDto with branchId, equipment list == null
//        CarDto carDto = buildCarDto(4L, false);
//        //when: mapping
//        CarEntity carEntity = carMapper.toCarEntity(carDto);
//        //then: CarEntity exists with branchId
//        checkCarEntityFieldVsCarDtoFields(carEntity, carDto);
//    }
//
//    @Test
//    public void shouldMapCarDtoToCarEntityWhenNoBranchIdSupplied() { // update
//        //given: CarDto with no branchId (case: updating car)
//        CarDto carDto = buildCarDto(null, false);
//        //when
//        CarEntity carEntity = carMapper.toCarEntity(carDto);
//        //then
//        checkCarEntityFieldVsCarDtoFields(carEntity, carDto);
//        Assertions.assertThat(carEntity.getBranch().getId()).isNull();
//        Assertions.assertThat(carEntity.getEquipment()).isNull();
//    }
//
//    @Test
//    public void shouldMapCarEntityToCarDto() {
//        BranchEntity branchEntityFromDbButOnlyWithId = new BranchEntity();
//        branchEntityFromDbButOnlyWithId.setId(55L);
//
//        List<EquipmentEntity> equipmentEntities = new ArrayList<>();
//        EquipmentEntity equipmentEntity = new EquipmentEntity();
//        equipmentEntity.setId(123L);
//        equipmentEntity.setName("Air conditioning");
//        equipmentEntities.add(equipmentEntity);
//
//        CarEntity carEntity = CarEntity.builder()
//                .id(777L)
//                .vin("4T1BK46K77U046314")
//                .make("Opel")
//                .model("Astra")
//                .bodyType(CarBodyTypeEnum.ESTATE)
//                .year(2015)
//                .colour(CarColourEnum.BLUE)
//                .mileage(70000)
//                .status(CarStatusEnum.IN_REPAIR)
//                .dailyRate(new BigDecimal("250.50"))
//                .engineType(CarEngineTypeEnum.DIESEL)
//                .engineCapacity(2100)
//                .segment(CarSegmentTypeEnum.D_LARGE)
//                .transmission(CarTransmissionTypeEnum.AUTOMATIC)
//                .seats(6)
//                .doors(5)
//                .branch(branchEntityFromDbButOnlyWithId)
//                .equipment(equipmentEntities)
//                .build();
//
//        //when: mapping
//        CarDto carDto = carMapper.toCarDto(carEntity);
//
//        //then: assertions
//        checkCarEntityFieldVsCarDtoFields(carEntity, carDto);
//        Assertions.assertThat(carEntity.getBranch().getId().equals(carDto.getBranchId()));
//        Assertions.assertThat(carEntity.getEquipment().equals(carDto.getEquipment()));
//
//    }
//
//    private CarDto buildCarDto(Long branchId, boolean carHasEquipment) {
//
//        List<EquipmentEntity> equipment = null;
//
//        if (carHasEquipment) {
//            equipment = new ArrayList<>();
//            equipment.add(new EquipmentEntity(1L, "Air conditioning"));
//            equipment.add(new EquipmentEntity(2L, "GPS navigation"));
//            equipment.add(new EquipmentEntity(3L, "Internet access"));
//        }
//
//        return CarDto.builder()
//                .carId(2L)
//                .vin("JH2SC2608SM506729")
//                .make("Ford")
//                .model("Focus")
//                .bodyType(CarBodyTypeEnum.SEDAN)
//                .year("2010")
//                .colour(CarColourEnum.WHITE)
//                .mileage(280000)
//                .status(CarStatusEnum.AVAILABLE)
//                .dailyRate(new BigDecimal("500.50"))
//                .engineType(CarEngineTypeEnum.PETROL)
//                .engineCapacity(1800)
//                .segment(CarSegmentTypeEnum.C_MEDIUM)
//                .transmission(CarTransmissionTypeEnum.MANUAL)
//                .seats(5)
//                .doors(4)
//                .branchId(branchId)
//                .equipment(equipment)
//                .build();
//    }
//
//    private void checkCarEntityFieldVsCarDtoFields(CarEntity carEntity, CarDto carDto) {
//        Assertions.assertThat(carEntity.getId().equals(carDto.getCarId()));
//        Assertions.assertThat(carEntity.getVin().equals(carDto.getVin()));
//        Assertions.assertThat(carEntity.getMake().equals(carDto.getMake()));
//        Assertions.assertThat(carEntity.getModel().equals(carDto.getModel()));
//        Assertions.assertThat(carEntity.getBodyType().equals(carDto.getBodyType()));
//        Assertions.assertThat(carEntity.getYear().equals(carDto.getYear()));
//        Assertions.assertThat(carEntity.getColour().equals(carDto.getColour()));
//        Assertions.assertThat(carEntity.getMileage().equals(carDto.getMileage()));
//        Assertions.assertThat(carEntity.getStatus().equals(carDto.getStatus()));
//        Assertions.assertThat(carEntity.getDailyRate().equals(carDto.getDailyRate()));
//        Assertions.assertThat(carEntity.getEngineType().equals(carDto.getEngineType()));
//        Assertions.assertThat(carEntity.getEngineCapacity().equals(carDto.getEngineCapacity()));
//        Assertions.assertThat(carEntity.getSegment().equals(carDto.getSegment()));
//        Assertions.assertThat(carEntity.getTransmission().equals(carDto.getTransmission()));
//        Assertions.assertThat(carEntity.getSeats().equals(carDto.getSeats()));
//        Assertions.assertThat(carEntity.getDoors().equals(carDto.getDoors()));
//
//    }


}