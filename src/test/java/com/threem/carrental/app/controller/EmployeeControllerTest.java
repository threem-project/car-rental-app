package com.threem.carrental.app.controller;

import com.threem.carrental.app.model.dto.EmployeeDto;
import com.threem.carrental.app.model.entity.BranchEntity;
import com.threem.carrental.app.model.entity.EmployeeEntity;
import com.threem.carrental.app.model.entity.enumTypes.EmployeeRoleEnum;
import com.threem.carrental.app.model.entity.enumTypes.EmployeeStatusEnum;
import com.threem.carrental.app.repository.EmployeeRepository;
import com.threem.carrental.app.service.EmployeeService;
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

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    private Random random = new Random();   //todo zapytać Roberta, czemu tutaj nie działa rollback przy testach i muszę używać Random

    @Test
    public void shouldGetStatusCreatedWhenReceiveProperEmployeeDtoForCreating() {
        EmployeeEntity employeeEntity = new EmployeeEntity().builder()   //given
                .id(null)
                .firstName("John")
                .lastName("Kowalski")
                .password("testPassword")
                .email("email@testdomain.com")
                .status(EmployeeStatusEnum.NEW)
                .role(EmployeeRoleEnum.REGULAR_EMPLOYEE)
                .branch(null)
                .build();

        //@formatter:off
        RequestSpecification given = given()
                .port(port)
                .body(employeeEntity)
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
        EmployeeEntity employeeEntity = new EmployeeEntity().builder()
                .firstName("test")
                .lastName("test")
                .password("testTest")
                .email("testtesttesttest")
                .status(EmployeeStatusEnum.ACTIVE)
                .role(EmployeeRoleEnum.OWNER)
                .branch(null)
                .build();

        employeeEntity = employeeRepository.save(employeeEntity);

        EmployeeEntity entityWithDuplicatedId = new EmployeeEntity().builder()
                .id(employeeEntity.getId())
                .firstName("John")
                .lastName("Kowalski")
                .password("testPassword")
                .email("email@testdomain.com")
                .status(EmployeeStatusEnum.NEW)
                .role(EmployeeRoleEnum.REGULAR_EMPLOYEE)
                .branch(null)
                .build();

        //when
        //@formatter:off
        RequestSpecification duplicatedGive = given()
                .port(port)
                .body(entityWithDuplicatedId)
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
        EmployeeEntity employeeEntity = new EmployeeEntity().builder()
                .id(null)
                .firstName("test")
                .lastName("test")
                .password("testTest")
                .email("testtesttesttest")
                .status(EmployeeStatusEnum.ACTIVE)
                .role(EmployeeRoleEnum.OWNER)
                .branch(new BranchEntity().builder().id(0L).build())
                .build();

        //@formatter:off
        RequestSpecification given = given()
                .port(port)
                .body(employeeEntity)
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
    public void shouldGetStatusAcceptedWhenReceiveProperEmployeeDtoForUpdate() {
        EmployeeEntity employeeEntity = new EmployeeEntity().builder()
                .firstName("test")
                .lastName("test")
                .password("testTest")
                .email("testtesttesttest")
                .status(EmployeeStatusEnum.ACTIVE)
                .role(EmployeeRoleEnum.OWNER)
                .branch(null)
                .build();

        employeeEntity = employeeRepository.save(employeeEntity);

        EmployeeDto updatedEmployeeDto = new EmployeeDto().builder() //given
                .employeeId(employeeEntity.getId())
                .firstName("John")
                .lastName("Kowalski")
                .password("testPassword")
                .email("email@testdomain.com")
                .status(EmployeeStatusEnum.NEW)
                .role(EmployeeRoleEnum.REGULAR_EMPLOYEE)
                .branchId(null)
                .build();

        //@formatter:off
        RequestSpecification putGiven = given()    //when
                .port(port)
                .body(updatedEmployeeDto)
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
        EmployeeDto updatedEmployeeDto = new EmployeeDto().builder() //given
                .employeeId(0L)
                .firstName("John")
                .lastName("Kowalski")
                .password("testPassword")
                .email("email@testdomain.com")
                .status(EmployeeStatusEnum.NEW)
                .role(EmployeeRoleEnum.REGULAR_EMPLOYEE)
                .branchId(null)
                .build();

        //@formatter:off
        RequestSpecification putGiven = given()    //when
                .port(port)
                .body(updatedEmployeeDto)
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
        EmployeeEntity employeeEntity = new EmployeeEntity().builder()  //given
                .firstName("test")
                .lastName("test")
                .password("testTest")
                .email("testtesttesttest")
                .status(EmployeeStatusEnum.ACTIVE)
                .role(EmployeeRoleEnum.OWNER)
                .branch(null)
                .build();

        employeeEntity = employeeRepository.save(employeeEntity);

        EmployeeDto updatedEmployeeDto = new EmployeeDto().builder()
                .employeeId(employeeEntity.getId())
                .firstName("John")
                .lastName("Kowalski")
                .password("testPassword")
                .email("email@testdomain.com")
                .status(EmployeeStatusEnum.NEW)
                .role(EmployeeRoleEnum.REGULAR_EMPLOYEE)
                .branchId(0L)
                .build();

        //@formatter:off
        RequestSpecification putGiven = given()    //when
                .port(port)
                .body(updatedEmployeeDto)
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
        //@formatter:off
        RequestSpecification getGiven = given()    //when
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
    public void shouldGetProperEmployeeDtoWhenEmployeeExisting() {
        EmployeeEntity employeeEntity = new EmployeeEntity().builder()  //given
                .firstName("test")
                .lastName("test")
                .password("testTest")
                .email("testtesttesttest")
                .status(EmployeeStatusEnum.ACTIVE)
                .role(EmployeeRoleEnum.OWNER)
                .branch(null)
                .build();

        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);

        //@formatter:off
        RequestSpecification getGiven = given()    //when
                .port(port)
                .pathParam("id",savedEmployeeEntity.getId())
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
        Map<String, Integer> parameters = new HashMap<>();
        parameters.put("currentPage",0);
        parameters.put("resultsPerPage",5);

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