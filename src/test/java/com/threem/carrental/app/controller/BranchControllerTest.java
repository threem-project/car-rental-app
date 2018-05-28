package com.threem.carrental.app.controller;

import com.threem.carrental.app.model.dto.AddressBranchDto;
import com.threem.carrental.app.model.dto.BranchDto;
import com.threem.carrental.app.model.entity.MainOfficeEntity;
import com.threem.carrental.app.model.entity.enumTypes.BranchStatusEnum;
import com.threem.carrental.app.repository.MainOfficeRepository;
import com.threem.carrental.app.service.BranchService;
import com.threem.carrental.app.service.mapper.MainOfficeMapper;
import io.restassured.mapper.ObjectMapper;
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
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author misza_lemko on 28.05.2018
 * @project car-rental-app
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Rollback
@Transactional
@ActiveProfiles("test")
public class BranchControllerTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    private BranchService branchService;
    @Autowired
    private static MainOfficeRepository mainOfficeRepository;
    @Autowired
    private MainOfficeMapper mainOfficeMapper;

    @Test
    public void shouldCreateAndReturnBranchWithStatusCreatedUsingBranchDto() throws Exception{
        //given
        BranchDto branchDto = BranchDto.builder()
                .id(1L)
                .status(BranchStatusEnum.OPENED)
                .build();
        AddressBranchDto addressBranchDto = AddressBranchDto.builder()
                .id(1L)
                .city("Warsaw")
                .street("Towarowa")
                .building("20/10")
                .zipCode("02-495")
                .country("Poland")
                .phone("111-222-333")
                .build();
        branchDto.setAddress(addressBranchDto);

        //@formatter:off
        RequestSpecification given = given()
                .port(port)
                .body(branchDto)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .log().all();

        Response when = given
                .when()
                .post("branch");

        when.then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());
        //@formatter:on
    }

    @Test
    public void shouldNotCreateBranchAndReturnStatusBadRequestUsingBranchDtoWithoutStatus() throws Exception{
        //given
        BranchDto branchDto = BranchDto.builder()
                .id(1L)
                .status(null)
                .build();
        AddressBranchDto addressBranchDto = AddressBranchDto.builder()
                .id(1L)
                .city("Warsaw")
                .street("Towarowa")
                .building("20/10")
                .zipCode("02-495")
                .country("Poland")
                .phone("111-222-333")
                .build();
        branchDto.setAddress(addressBranchDto);

        //@formatter:off
        RequestSpecification given = given()
                .port(port)
                .body(branchDto)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .log().all();

        Response when = given
                .when()
                .post("branch");

        when.then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
        //@formatter:on
    }
}