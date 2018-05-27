package com.threem.carrental.app.controller;

import com.threem.carrental.app.model.dto.EmployeeDto;
import com.threem.carrental.app.model.entity.enumTypes.EmployeeRoleEnum;
import com.threem.carrental.app.model.entity.enumTypes.EmployeeStatusEnum;
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

    private Random random = new Random();   //todo zapytać Roberta, czemu tutaj nie działa rollback przy testach i muszę używać Random

    @Test
    public void shouldGetStatusCreatedWhenReceiveProperEmployeeDtoForCreating() {
        EmployeeDto employeeDto = new EmployeeDto().builder()   //given
                .employeeId(null)
                .firstName("John")
                .lastName("Kowalski")
                .password("testPassword")
                .email("email@testdomain.com")
                .status(EmployeeStatusEnum.NEW)
                .role(EmployeeRoleEnum.REGULAR_EMPLOYEE)
                .branchId(null)
                .build();

        //@formatter:off
        RequestSpecification given = given()
                .port(port)
                .body(employeeDto)
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
        Long id = Long.valueOf(random.nextInt());

        EmployeeDto employeeDto = new EmployeeDto().builder()   //given
                .employeeId(Long.valueOf(id))
                .firstName("test")
                .lastName("test")
                .password("testTest")
                .email("testtesttesttest")
                .status(EmployeeStatusEnum.ACTIVE)
                .role(EmployeeRoleEnum.OWNER)
                .branchId(null)
                .build();

        EmployeeDto employeeDtoWithDuplicatedId = new EmployeeDto().builder()
                .employeeId(Long.valueOf(id))
                .firstName("John")
                .lastName("Kowalski")
                .password("testPassword")
                .email("email@testdomain.com")
                .status(EmployeeStatusEnum.NEW)
                .role(EmployeeRoleEnum.REGULAR_EMPLOYEE)
                .branchId(null)
                .build();

        //@formatter:off
        RequestSpecification given = given()
                .port(port)
                .body(employeeDto)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .log().all();
        Response when = given
                .when()
                .post("employee");
        when.then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());

        //--

        RequestSpecification duplicatedGive = given()    //when
                .port(port)
                .body(employeeDtoWithDuplicatedId)
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
        EmployeeDto employeeDto = new EmployeeDto().builder()   //given
                .employeeId(Long.valueOf(1))
                .firstName("test")
                .lastName("test")
                .password("testTest")
                .email("testtesttesttest")
                .status(EmployeeStatusEnum.ACTIVE)
                .role(EmployeeRoleEnum.OWNER)
                .branchId(Long.valueOf(1))
                .build();

        //@formatter:off
        RequestSpecification given = given()
                .port(port)
                .body(employeeDto)
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
        Integer id = random.nextInt();
        EmployeeDto employeeDto = new EmployeeDto().builder()   //given
                .employeeId(Long.valueOf(id))
                .firstName("test")
                .lastName("test")
                .password("testTest")
                .email("testtesttesttest")
                .status(EmployeeStatusEnum.ACTIVE)
                .role(EmployeeRoleEnum.OWNER)
                .branchId(null)
                .build();

        EmployeeDto updatedEmployeeDto = new EmployeeDto().builder()
                .employeeId(Long.valueOf(id))
                .firstName("John")
                .lastName("Kowalski")
                .password("testPassword")
                .email("email@testdomain.com")
                .status(EmployeeStatusEnum.NEW)
                .role(EmployeeRoleEnum.REGULAR_EMPLOYEE)
                .branchId(null)
                .build();

        //@formatter:off
        RequestSpecification given = given()
                .port(port)
                .body(employeeDto)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .log().all();
        Response when = given
                .when()
                .post("employee");
        when.then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());

        //--

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
        EmployeeDto employeeDto = new EmployeeDto().builder()   //given
                .employeeId(Long.valueOf(1))
                .firstName("test")
                .lastName("test")
                .password("testTest")
                .email("testtesttesttest")
                .status(EmployeeStatusEnum.ACTIVE)
                .role(EmployeeRoleEnum.OWNER)
                .branchId(null)
                .build();

        EmployeeDto updatedEmployeeDto = new EmployeeDto().builder()
                .employeeId(Long.valueOf(2))
                .firstName("John")
                .lastName("Kowalski")
                .password("testPassword")
                .email("email@testdomain.com")
                .status(EmployeeStatusEnum.NEW)
                .role(EmployeeRoleEnum.REGULAR_EMPLOYEE)
                .branchId(null)
                .build();

        //@formatter:off
        RequestSpecification given = given()
                .port(port)
                .body(employeeDto)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .log().all();
        Response when = given
                .when()
                .post("employee");
        when.then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());

        //--

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
        Long id = random.nextLong();

        EmployeeDto employeeDto = new EmployeeDto().builder()   //given
                .employeeId(Long.valueOf(id))
                .firstName("test")
                .lastName("test")
                .password("testTest")
                .email("testtesttesttest")
                .status(EmployeeStatusEnum.ACTIVE)
                .role(EmployeeRoleEnum.OWNER)
                .branchId(null)
                .build();

        EmployeeDto updatedEmployeeDto = new EmployeeDto().builder()
                .employeeId(Long.valueOf(id))
                .firstName("John")
                .lastName("Kowalski")
                .password("testPassword")
                .email("email@testdomain.com")
                .status(EmployeeStatusEnum.NEW)
                .role(EmployeeRoleEnum.REGULAR_EMPLOYEE)
                .branchId(Long.valueOf(2))
                .build();

        //@formatter:off
        RequestSpecification given = given()
                .port(port)
                .body(employeeDto)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .log().all();
        Response when = given
                .when()
                .post("employee");
        when.then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());

        //--

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
}