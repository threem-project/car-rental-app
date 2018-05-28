package com.threem.carrental.app.service;

import com.threem.carrental.app.model.dto.CarDto;
import com.threem.carrental.app.model.dto.EmployeeDto;
import com.threem.carrental.app.model.entity.AddressBranchEntity;
import com.threem.carrental.app.model.entity.BranchEntity;
import com.threem.carrental.app.model.entity.CarEntity;
import com.threem.carrental.app.model.entity.EmployeeEntity;
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
    private BranchRepository branchRepository;

    @Test
    public void shouldCreateCarWhenReceiveProperCarDto() {

        // given: proper CarDto & chosen BranchEntity present in DB

        BranchEntity branchEntity = new BranchEntity().builder()
                .address(new AddressBranchEntity())
                .build();

        branchEntity = branchRepository.save(branchEntity);

        CarDto carDto = new CarDto().builder()
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
                .seats(5)
                .doors(5)
                .branchId(branchEntity.getId())
                .equipment(null)
//                .photoUrl("https://fakeimageurl.pl")
                .build();

        // when: run create method

        Optional<CarDto> carDtoFromDb = carService.createCar(carDto);

        // then:
        // save() worked and returned updated dto
        // it has a id not null and all fields as expected

        Long carId = 0L;
        if (carDtoFromDb.isPresent()) {
            carId = carDtoFromDb.get().getCarId();
        }
        Assertions.assertThat(carId != 0L);



    }
}

//
//public class EmployeeServiceTest {
//
//
//
//    @Test
//    public void shouldCreateEmployeeWhenReceiveProperEmployeeDto() throws InterruptedException {
//        EmployeeDto employeeDto = new EmployeeDto().builder()   //given
//                .employeeId(null)
//                .firstName("John")
//                .lastName("Kowalski")
//                .password("testPassword")
//                .email("email@testdomain.com")
//                .status(EmployeeStatusEnum.NEW)
//                .role(EmployeeRoleEnum.REGULAR_EMPLOYEE)
//                .branchId(Long.valueOf(1))
//                .build();
//
//        Optional<EmployeeDto> createdDto = employeeService.createEmployee(employeeDto); //when
//
//        Assertions.assertThat(createdDto.get())  //then
//                .hasFieldOrPropertyWithValue("firstName","John")
//                .hasFieldOrPropertyWithValue("lastName","Kowalski")
//                .hasFieldOrPropertyWithValue("password",null)
//                .hasFieldOrPropertyWithValue("email","email@testdomain.com")
//                .hasFieldOrPropertyWithValue("status",EmployeeStatusEnum.NEW)
////                .hasFieldOrPropertyWithValue("branchId",null) //todo refactor this as soon, as there is branchEntity implementation
//                .hasFieldOrPropertyWithValue("role",EmployeeRoleEnum.REGULAR_EMPLOYEE);
//
//        Assertions.assertThat(createdDto.get().getEmployeeId())
//                .isNotNull();
//    }
//
//}
//*
//* */