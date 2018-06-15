package com.threem.carrental.app.controller;

import com.threem.carrental.app.model.entity.BranchEntity;
import com.threem.carrental.app.model.entity.EmployeeEntity;
import com.threem.carrental.app.model.entity.enumTypes.EmployeeRoleEnum;
import com.threem.carrental.app.model.entity.enumTypes.EmployeeStatusEnum;
import com.threem.carrental.app.repository.EmployeeRepository;
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

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


/**
 * @author marek_j on 2018-05-24
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Rollback
@ActiveProfiles("test")
public class EmployeeControllerTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    private EmployeeRepository employeeRepository;

    private EmployeeEntity testEmployee;
    private EmployeeEntity testEmployeeUpdated;

    @Before
    public void setup() {
        testEmployee = new EmployeeEntity().builder()
                .id(null)
                .firstName("John")
                .lastName("Kowalski")
                .password("testPassword")
                .email("email@testdomain.com")
                .status(EmployeeStatusEnum.NEW)
                .role(EmployeeRoleEnum.REGULAR_EMPLOYEE)
                .branch(null)
                .build();

        testEmployeeUpdated = new EmployeeEntity().builder()
                .id(null)
                .firstName("test")
                .lastName("test")
                .password("testtest")
                .email("testTest")
                .status(EmployeeStatusEnum.ACTIVE)
                .role(EmployeeRoleEnum.OWNER)
                .branch(null)
                .build();
    }

    @Test
    public void shouldGetStatusCreatedWhenReceiveProperEmployeeForCreating() {
        //then
        //@formatter:off
        RequestSpecification given = given()
                .port(port)
                .body(testEmployee)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .log().all();
        Response when = given
                .when()
                .post("employee");
        when.then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());
        //@formatter:on
    }

    @Test
    public void shouldGetStatusUnprocessableEntityWhenCreatingEmployeeWithTheSameIdAsInDb() {
        //given
        employeeRepository.save(testEmployee);
        //when
        testEmployeeUpdated.setId(testEmployee.getId());
        //then
        //@formatter:off
        RequestSpecification duplicatedGive = given()
                .port(port)
                .body(testEmployeeUpdated)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .log().all();
        Response duplicatedWhen = duplicatedGive
                .when()
                .post("employee");
        duplicatedWhen.then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
        //@formatter:on
    }

    @Test
    public void shouldGetStatusUnprocessableEntityWhenCreatingEmployeeWithWrongBranchId() {
        //given
        //when
        testEmployee.setBranch(new BranchEntity().builder().id(0L).build());
        //then
        //@formatter:off
        RequestSpecification given = given()
                .port(port)
                .body(testEmployee)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .log().all();
        Response when = given
                .when()
                .post("employee");
        when.then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
        //@formatter:on
    }

    @Test
    public void shouldGetStatusAcceptedWhenReceiveProperEmployeeForUpdate() {
        //given
        employeeRepository.save(testEmployee);
        //when
        testEmployeeUpdated.setId(testEmployee.getId());
        //then
        //@formatter:off
        RequestSpecification putGiven = given()    //when
                .port(port)
                .body(testEmployeeUpdated)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .log().all();
        Response putWhen = putGiven
                .when()
                .put("employee");
        putWhen.then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.ACCEPTED.value());
        //@formatter:on
    }

    @Test
    public void shouldGetStatusUnprocessableEntityWhenUpdatingNonExistingEmployee() {
        //given
        //when
        testEmployee.setId(0L);
        //then
        //@formatter:off
        RequestSpecification putGiven = given()    //when
                .port(port)
                .body(testEmployee)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .log().all();
        Response putWhen = putGiven
                .when()
                .put("employee");
        putWhen.then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
        //@formatter:on
    }

    @Test
    public void shouldGetStatusUnprocessableEntityWhenUpdatingEmployeeWithWrongBranchId() {
        //given
        employeeRepository.save(testEmployee);
        //when
        testEmployeeUpdated.setId(testEmployee.getId());
        testEmployeeUpdated.setBranch(new BranchEntity().builder().id(0L).build());
        //then
        //@formatter:off
        RequestSpecification putGiven = given()
                .port(port)
                .body(testEmployeeUpdated)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .log().all();
        Response putWhen = putGiven
                .when()
                .put("employee");
        putWhen.then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
        //@formatter:on
    }

    @Test
    public void shouldGetBadRequestForIncorrectIdFormat() {
        //given
        //when
        //then
        //@formatter:off
        RequestSpecification getGiven = given()
                .port(port)
                .pathParam("id","x")
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .log().all();
        Response getWhen = getGiven
                .when()
                .get("employee/{id}");
        getWhen.then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
        //@formatter:on
    }

    @Test
    public void shouldGetNotFoundStatusWhenEmployeeNotExisting() {
        //given
        //when
        //then
        //@formatter:off
        RequestSpecification getGiven = given()    //when
                .port(port)
                .pathParam("id",0)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .log().all();
        Response getWhen = getGiven
                .when()
                .get("employee/{id}");
        getWhen.then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value());
        //@formatter:on
    }

    @Test
    public void shouldGetProperEmployeeWhenEmployeeExisting() {
        //given
        //when
        employeeRepository.save(testEmployee);
        //then
        //@formatter:off
        RequestSpecification getGiven = given()    //when
                .port(port)
                .pathParam("id",testEmployee.getId())
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .log().all();
        Response getWhen = getGiven
                .when()
                .get("employee/{id}");
        getWhen.then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.OK.value());
        //@formatter:on
    }

    @Test
    public void shouldGetStatus200WhileGettingAllEmployeesPaginatedCorrectly() {
        //given
        //when
        Map<String, Integer> parameters = new HashMap<>();
        parameters.put("currentPage",0);
        parameters.put("resultsPerPage",5);
        //then
        //@formatter:off
        RequestSpecification getGiven = given()    //when
                .port(port)
                .params(parameters)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .log().all();
        Response getWhen = getGiven
                .when()
                .get("employee");
        getWhen.then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.OK.value());
        //@formatter:on
    }

}