package com.threem.carrental.app.controller;

import com.threem.carrental.app.model.dto.AddressBranchDto;
import com.threem.carrental.app.model.dto.BranchDto;
import com.threem.carrental.app.model.dto.CarDto;
import com.threem.carrental.app.model.entity.enumTypes.*;
import com.threem.carrental.app.service.BranchService;
import com.threem.carrental.app.service.CarService;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author Marika Grzebieniowska on 27.05.2018
 * @project car-rental-app
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Rollback
@Transactional
@ActiveProfiles("test")
public class CarControllerTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    private BranchService branchService;

    @Test
    public void shouldCreateNewCarUponReceivingProperCarDto() { 

        // given
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
        Optional<BranchDto> branchDtoFromDb;
        branchDtoFromDb = Optional.of(branchService.createBranch(branchDto));

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
                .seats(6)
                .doors(3)
                .branchId(12L)
                .equipment(null)
//                .photoUrl("https://fakeimageurl.pl")
                .build();

        RequestSpecification given = given()
                .port(port)
                .body(carDto)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .log().all();

        Response when = given
                .when()
                .post("car");

        when.then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());
    }

    // TODO test: failing validation? wrong vin?
}