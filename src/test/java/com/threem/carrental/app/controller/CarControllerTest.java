package com.threem.carrental.app.controller;

import com.threem.carrental.app.model.entity.CarEntity;
import com.threem.carrental.app.repository.CarRepository;
import com.threem.carrental.testEntitiesFactory.TestCarEntityFactory;
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

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author marek_j on 2018-06-26
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Rollback
@ActiveProfiles("test")
public class CarControllerTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    private CarRepository carRepository;

    private TestCarEntityFactory carFactory;
    private CarEntity fordAutomaticPetrol;

    @Before
    public void setup() {
        this.carFactory = new TestCarEntityFactory();
        this.fordAutomaticPetrol = carFactory.getEntity("FORD_RANDOM_VIN_4_SEATS_4_DOORS_4000_CCM_NO_EQUIPMENT_AUTOMATIC");
    }

    @Test
    public void shouldGiveStatusCreatedWithPostOfCorrectCarEntity() {
        //@formatter:off
        given()
                .port(port)
                .body(fordAutomaticPetrol)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .log().all()
        .when()
                .post("car")

        .then()
                .log().all()
                .assertThat()
                .statusCode(201)
                .body("make", containsString("Ford"))
                .body("model",containsString("TestMondeo"));
        //@formatter:on
    }

    @Test
    public void shouldGiveUnprocessableEntityWithPostOfAlreadyExistingEntity() {
        //given
        carRepository.save(fordAutomaticPetrol);

        //@formatter:off
        given()
                .port(port)
                .body(fordAutomaticPetrol)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .log().all()
        .when()
                .post("car")
        .then()
                .log().all()
        .assertThat()
                .statusCode(422);
        //@formatter:on
    }

    @Test
    public void shouldGiveStatusCreatedWithPutOfCorrectEntity() {
        //given
        carRepository.save(fordAutomaticPetrol);

        //when
        fordAutomaticPetrol.setMake("JamJestFordTwoj");
        fordAutomaticPetrol.setModel("FordJuzTutajByl");

        //@formatter:off
        given()
                .port(port)
                .body(fordAutomaticPetrol)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .log().all()
        .when()
                .put("car")
        .then()
                .log().all()
        .assertThat()
                .statusCode(201)
                .body("make",containsString("JamJestFordTwoj"))
                .body("model",containsString("FordJuzTutajByl"));
        //@formatter:on
    }

    @Test
    public void shouldGiveStatusUnprocessableEntityWhenPutEntityNotExistingInDb() {
        //when
        fordAutomaticPetrol.setId(-1L);

        //@formatter:off
        given()
                .port(port)
                .body(fordAutomaticPetrol)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .log().all()
        .when()
                .put("car")
        .then()
                .log().all()
        .assertThat()
                .statusCode(422);
        //@formatter:on
    }

    @Test
    public void shouldGiveStatusOkAndGiveEntityWhenGetForExistingCarId() {
        //given
        fordAutomaticPetrol.setMake("JamJestFordTwoj");
        fordAutomaticPetrol.setModel("FordJuzTutajByl");

        //when
        carRepository.save(fordAutomaticPetrol);

        //then
        //@formatter:off
        given()
                .port(port)
                .pathParam("id",fordAutomaticPetrol.getId())
                .body(fordAutomaticPetrol)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .log().all()
        .when()
                .get("car/{id}")
        .then()
                .log().all()
        .assertThat()
                .statusCode(200)
                .body("make",containsString("JamJestFordTwoj"))
                .body("model",containsString("FordJuzTutajByl"));
        //@formatter:on
    }

    @Test
    public void shouldGetBadRequestForIncorrectIdFormat() {
        //given
        //when
        //then
        //@formatter:off
        given()
                .port(port)
                .pathParam("id","x")
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .log().all()
        .when()
                .get("car/{id}")
        .then()
                .log().all()
        .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
        //@formatter:on
    }

    @Test
    public void shouldGetNotFoundStatusWhenCarIsNotExisting() {
        //@formatter:off
        given()
                .port(port)
                .pathParam("id",0)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .log().all()
        .when()
                .get("car/{id}")
        .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value());
        //@formatter:on
    }

}
