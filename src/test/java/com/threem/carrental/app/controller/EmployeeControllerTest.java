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

import static io.restassured.RestAssured.given;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


/**
 * @author marek_j on 2018-05-24
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Rollback
@Transactional
@ActiveProfiles("test")
public class EmployeeControllerTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void shouldCreateNewEmployeeWhenReceiveProperEmployeeDto() {
        EmployeeDto employeeDto = new EmployeeDto().builder()   //given
                .employeeId(null)
                .firstName("John")
                .lastName("Kowalski")
                .password("testPassword")
                .email("email@testdomain.com")
                .status(EmployeeStatusEnum.NEW)
                .role(EmployeeRoleEnum.REGULAR_EMPLOYEE)
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
                .statusCode(HttpStatus.CREATED.value());

        //@formatter:on
    }

}