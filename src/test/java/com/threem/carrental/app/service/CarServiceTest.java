package com.threem.carrental.app.service;

import com.threem.carrental.app.model.dto.AddressBranchDto;
import com.threem.carrental.app.model.dto.BranchDto;
import com.threem.carrental.app.model.dto.CarDto;
import com.threem.carrental.app.model.dto.EmployeeDto;
import com.threem.carrental.app.model.entity.*;
import com.threem.carrental.app.model.entity.enumTypes.*;
import com.threem.carrental.app.repository.BranchRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
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
    private BranchService branchService;
    @Autowired
    private BranchRepository branchRepository;

    @Test
    public void shouldCreateCarWhenReceivesProperCarDto() {

        // given: proper CarDto & chosen BranchEntity present in DB
        BranchDto branchDto = BranchDto.builder()
                .id(12L)
                .status(BranchStatusEnum.OPENED)
                .build();
        AddressBranchDto addressBranchDto = AddressBranchDto.builder()
                .id(123L)
                .city("Warsaw")
                .street("Towarowa")
                .building("20/10")
                .zipCode("02-495")
                .country("Poland")
                .phone("111-222-333")
                .build();
        branchDto.setAddress(addressBranchDto);

        // calls mapper and saves BranchEntity in DB
        BranchDto createdBranch = branchService.createBranch(branchDto);

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
                .branchId(createdBranch.getId())
                .equipment(null)
//                .photoUrl("https://fakeimageurl.pl")
                .build();

        // when: run create method
        Optional<CarDto> carDtoFromDb = carService.createCar(carDto);

        // then:
        // save() worked and returned updated dto
        // it has a carId and all fields as expected

        if (carDtoFromDb.isPresent()) {
            Assertions.assertThat(carDtoFromDb.get())
                    .hasFieldOrPropertyWithValue("carId", 1L)
                    .hasFieldOrPropertyWithValue("vin", "JH2SC2608SM506729");

            Assertions.assertThat(carDtoFromDb.get().getBranchId() == 12L);
        }
    }
}
