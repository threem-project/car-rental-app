package com.threem.carrental.app.service;

import com.threem.carrental.app.errorHandler.customExceptions.CarAlreadyExistsException;
import com.threem.carrental.app.model.dto.CarDto;
import com.threem.carrental.app.model.entity.*;
import com.threem.carrental.app.model.entity.enumTypes.*;
import com.threem.carrental.app.repository.BranchRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Optional;

/**
 * @author Marika Grzebieniowska on 28.05.2018
 * @project car-rental-app
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Rollback
@Transactional
@ActiveProfiles("test")
public class CarServiceTest {

    @Autowired
    private CarService carService;

    @Autowired
    private BranchRepository branchRepository;

    @Test
    public void shouldCreateCarWhenReceivesProperCarDtoWithProperBranch() {

        // given: proper CarDto & chosen BranchEntity present in DB

        BranchEntity branchEntity = new BranchEntity();
        branchRepository.save(branchEntity);

        CarDto carDto = CarDto.builder()
                .carId(null)
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
                .seats(4)
                .doors(5)
                .branchId(branchEntity.getId())
                .equipment(null)
                //.photoUrl("https://fakeimage.pl")
                .build();

        // when: run create method

        Optional<CarDto> carDtoFromDb = carService.createCar(carDto);

        // then:

        Assertions.assertThat(carDtoFromDb.get().getCarId()).isNotNull(); // carId was generated

        Assertions.assertThat(carDtoFromDb.get())
                .hasFieldOrPropertyWithValue("vin", "JH2SC2608SM506729")
                .hasFieldOrPropertyWithValue("branchId", branchEntity.getId())
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
                .hasFieldOrPropertyWithValue("seats", 4)
                .hasFieldOrPropertyWithValue("doors", 5)
                .hasFieldOrPropertyWithValue("branchId", branchEntity.getId())
                .hasFieldOrPropertyWithValue("equipment", null);

    }

    @Test(expected = CarAlreadyExistsException.class)
    public void shouldThrowCarAlreadyExistsExceptionWhenVinAlreadyInDb() {

        // given: a CarDto with Vin which is already in Db

        BranchEntity branchEntity = new BranchEntity();
        branchRepository.save(branchEntity);

        CarDto carDto = CarDto.builder()
                .carId(null)
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
                .seats(4)
                .doors(5)
                .branchId(branchEntity.getId()) // will be autogenerated
                .equipment(null)
                //.photoUrl("https://fakeimage.pl")
                .build();

        Optional<CarDto> carDtoFromDb = carService.createCar(carDto);

        CarDto carDto2 = CarDto.builder()
                .carId(null)
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
                .seats(4)
                .doors(5)
                .branchId(branchEntity.getId()) // will be autogenerated
                .equipment(null)
                //.photoUrl("https://fakeimage.pl")
                .build();

             // when: try saving car

        Optional<CarDto> carDtoFromDb2 = carService.createCar(carDto);

        // then: throw exception


    }


}
