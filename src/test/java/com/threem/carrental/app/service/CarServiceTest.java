package com.threem.carrental.app.service;

import com.threem.carrental.app.errorHandler.customExceptions.CarAlreadyExistsException;
import com.threem.carrental.app.model.entity.CarEntity;
import com.threem.carrental.app.model.entity.EquipmentEntity;
import com.threem.carrental.app.model.entity.enumTypes.CarEquipmentEnum;
import com.threem.carrental.app.repository.CarRepository;
import com.threem.carrental.factory.TestCarEquipmentFactory;
import com.threem.carrental.factory.TestCarEntityFactory;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author marek_j on 2018-06-24
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Rollback
@ActiveProfiles("test")
@Transactional
public class CarServiceTest {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarService carService;

    private TestCarEntityFactory carFactory;
    private CarEntity fordAutomaticPetrol;
    private CarEntity skodaManualDiesel;
    private TestCarEquipmentFactory equipmentFactory;
    private List<EquipmentEntity> airConditioning;
    private List<EquipmentEntity> airConditioningAndGps;

    @Before
    public void setup() {
        this.carFactory = new TestCarEntityFactory();
        this.fordAutomaticPetrol = carFactory.getEntity("FORD_RANDOM_VIN_4_SEATS_4_DOORS_4000_CCM_NO_EQUIPMENT_AUTOMATIC");
        this.skodaManualDiesel = carFactory.getEntity("SKODA_RANDOM_VIN_3_SEATS_3_DOORS_3000_CCM_NO_EQUIPMENT_MANUAL");
        this.equipmentFactory = new TestCarEquipmentFactory();
        this.airConditioning = equipmentFactory.getEquipment("TEST_AIR_CONDITIONING");
        this.airConditioningAndGps = equipmentFactory.getEquipment("TEST_AIR_CONDITIONING_GPS");
    }

    @Test
    public void shouldCreateNewCarWithCorrectEntity() {
        //given
        //when
        Optional<CarEntity> carOptionalFromService = carService.createCar(fordAutomaticPetrol);
        CarEntity carEntityFromService = carOptionalFromService.get();
        //then
        CarEntity carFromDb = carRepository.findByIdAndVin(carEntityFromService.getId(),carEntityFromService.getVin());
        Assertions.assertThat(carEntityFromService)
                .isEqualToComparingFieldByField(carFromDb)
                .isEqualToComparingFieldByField(fordAutomaticPetrol);
    }

    @Test
    public void shouldCreateNewCarWithCorrectEntityWithEquipment() {
        //given
        //when
        fordAutomaticPetrol.setEquipment(airConditioningAndGps);
        Optional<CarEntity> carOptionalFromService = carService.createCar(fordAutomaticPetrol);
        CarEntity carEntityFromService = carOptionalFromService.get();
        //then
        CarEntity carFromDb = carRepository.findByIdAndVin(carEntityFromService.getId(),carEntityFromService.getVin());
        Assertions.assertThat(carEntityFromService)
                .isEqualToComparingFieldByField(carFromDb)
                .isEqualToComparingFieldByField(fordAutomaticPetrol);
    }

    @Test(expected = CarAlreadyExistsException.class)
    public void shouldThrowExceptionWhenCreatingCarWithExistingId() {
        //given
        carRepository.save(fordAutomaticPetrol);
        Long duplicatedId = fordAutomaticPetrol.getId();
        //when
        skodaManualDiesel.setId(duplicatedId);
        //then
        carService.createCar(skodaManualDiesel);
    }

    @Test(expected = CarAlreadyExistsException.class)
    public void shouldThrowExceptionWhenCreatingCarWithExistingVin() {
        //given
        carRepository.save(fordAutomaticPetrol);
        String duplicatedVin = fordAutomaticPetrol.getVin();
        //when
        skodaManualDiesel.setVin(duplicatedVin);
        //then
        carService.createCar(skodaManualDiesel);
    }

    @Test
    public void shouldUpdateCarWhenIdExistInDb() {
        //given
        fordAutomaticPetrol.setEquipment(airConditioning);
        carRepository.save(fordAutomaticPetrol);
        Long duplicatedId = fordAutomaticPetrol.getId();
        String duplicatedVin = fordAutomaticPetrol.getVin();
        //when
        skodaManualDiesel.setId(duplicatedId);
        skodaManualDiesel.setVin(duplicatedVin);
        skodaManualDiesel.setEquipment(airConditioningAndGps);
        Optional<CarEntity> carOptionalFromService = carService.updateCar(skodaManualDiesel);
        CarEntity carEntityFromService = carOptionalFromService.get();
        //then
        Assertions.assertThat(carEntityFromService).isEqualToComparingFieldByField(skodaManualDiesel);
    }

}
