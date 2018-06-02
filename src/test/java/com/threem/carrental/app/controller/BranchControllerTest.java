package com.threem.carrental.app.controller;

import com.threem.carrental.app.model.dto.AddressBranchDto;
import com.threem.carrental.app.model.dto.BranchDto;
import com.threem.carrental.app.model.entity.enumTypes.BranchStatusEnum;
import com.threem.carrental.app.repository.MainOfficeRepository;
import com.threem.carrental.app.service.BranchService;
import com.threem.carrental.app.service.mapper.MainOfficeMapper;
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

import static io.restassured.RestAssured.given;
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

    @Test
    public void shouldCreateAndReturnBranchWithStatusCreatedUsingBranchDto() throws Exception{
        //given
        BranchDto branchDto = BranchDto.builder()
                .status(BranchStatusEnum.OPEN)
                .build();
        AddressBranchDto addressBranchDto = AddressBranchDto.builder()
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
                .status(null)
                .build();
        AddressBranchDto addressBranchDto = AddressBranchDto.builder()
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

    @Test
    public void shouldFindBranchByIdAndReturnOkStatusAndBranch() throws Exception{
        //given
        Long testId = 1L;
        //@formatter:off
        RequestSpecification getGiven = given()    //when
                .port(port)
                .pathParam("id",testId)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .log().all();
        Response getWhen = getGiven
                .when()
                .get("branch/{id}");
        getWhen.then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.OK.value());
        //@formatter:on
    }

    @Test
    public void shouldGetNoContentStatusWhenTryFindBranchById() throws Exception {
        //given
        Long testId = 0L;
        //@formatter:off
        RequestSpecification getGiven = given()    //when
                .port(port)
                .pathParam("id",testId)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .log().all();
        Response getWhen = getGiven
                .when()
                .get("branch/{id}");
        getWhen.then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.NO_CONTENT.value());
        //@formatter:on
    }

    @Test
    public void shouldGetBadRequestStatusWhenTryFindBranchByIdOfNotLongType() throws Exception {
        //given
        String testId = "testId";
        //@formatter:off
        RequestSpecification getGiven = given()    //when
                .port(port)
                .pathParam("id",testId)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .log().all();
        Response getWhen = getGiven
                .when()
                .get("branch/{id}");
        getWhen.then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
        //@formatter:on
    }
}