package com.threem.carrental.app.controller;

import com.threem.carrental.app.model.dto.CarDto;
import com.threem.carrental.app.model.entity.BranchEntity;
import com.threem.carrental.app.model.entity.enumTypes.*;
import com.threem.carrental.app.repository.BranchRepository;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

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
@ActiveProfiles("test")
public class CarControllerTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    private BranchRepository branchRepository;

    @Test
    public void shouldCreateNewCarUponReceivingProperCarDtoWithProperBranchSet() {
        CarDto carDto = buildCarDto("2C3CCAPT7CH236207");
        givenWhenThen(carDto, HttpStatus.CREATED);
    }

    @Test
    public void shouldThrowValidationExceptionWhenReceivedVinTooShort() {
        CarDto carDto = buildCarDto("1GNCS13W1Y404");
        givenWhenThen(carDto, HttpStatus.BAD_REQUEST);
    }

    private CarDto buildCarDto(String vin) {
        BranchEntity branchEntity = new BranchEntity();
        branchRepository.save(branchEntity);
        return CarDto.builder()
                .carId(null)
                .vin(vin)
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
                .branchId(branchEntity.getId())
                .equipment(null)
                .build();
    }

    private void givenWhenThen(CarDto carDto, HttpStatus expectedHttpStatusCode) {
        RequestSpecification given = given()
                .port(port)
                .body(carDto)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .log().all();

        Response when = given
                .when().post("car");

        when.then()
                .log().all()
                .assertThat()
                .statusCode(expectedHttpStatusCode.value());
    }

}