package com.threem.carrental.app.service;

import com.threem.carrental.app.errorHandler.customExceptions.CarAlreadyExistsException;
import com.threem.carrental.app.errorHandler.customExceptions.IncorrectBranchException;
import com.threem.carrental.app.model.dto.CarDto;
import com.threem.carrental.app.model.entity.*;
import com.threem.carrental.app.model.entity.enumTypes.*;
import com.threem.carrental.app.repository.BranchRepository;
import com.threem.carrental.app.repository.CarRepository;
import com.threem.carrental.app.repository.EquipmentRepository;
import com.threem.carrental.app.service.mapper.CarMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.internal.verification.VerificationModeFactory.times;


/**
 * @author Marika Grzebieniowska on 28.05.2018
 * @project car-rental-app
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Rollback
@Transactional
@ActiveProfiles("test")
public class CarServiceTest {

    @Mock
    private CarRepository carRepositoryMock;

    @Mock
    private BranchRepository branchRepositoryMock;

    @Mock
    private CarMapper carMapperMock;

    @Mock
    private EquipmentRepository equipmentRepositoryMock;

    @InjectMocks
    private CarService carService;

    @Test(expected = CarAlreadyExistsException.class)
    public void shouldThrowCarAlreadyExistsExceptionWhenVinAlreadyInDb() {

        // need to mock carRepository.findByVin() to act as if it found a Car with this Vin
        // meaning it must return a CarEntity object

        Mockito.when(carRepositoryMock.findByVin("3GNBACDV2AS588224")).thenReturn(new CarEntity());

        // given: a CarDto with already present vin

        CarDto carDto = buildCarDto(1L, "3GNBACDV2AS588224", new BranchEntity(), new ArrayList<>());

        // when: createCar(CarDto)

        carService.createCar(carDto);

        // then: exception thrown

    }

    @Test(expected = IncorrectBranchException.class)
    public void shouldThrowIncorrectBranchExceptionWhenCarDtoHasIncorrectBranchId() {

        // need to mock carRepository.findByVin() to act as if it didn't find a Car with this Vin = return null instead of CarEntity

        Mockito.when(carRepositoryMock.findByVin("WBA5B3C58ED532909")).thenReturn(null);

        // need to mock carMapper.toCarEntity() to return CarEntity with dummy BranchEntity with branchId taken
        // from CarDto, so not to test mapper here (it will not be null, because @Valiated checks for this already)

        CarDto carDto = buildCarDto(null, "WBA5B3C58ED532909", buildBranchEntity(333L), buildEquipment());
        CarEntity carEntity = buildCarEntity(carDto);

        Mockito.when(carMapperMock.toCarEntity(Mockito.any(CarDto.class))).thenReturn(carEntity);

        // need to mock branchRepository.findById(carDto.getBranchId() to return Optional.empty() to indicate given branch doesn't exist in DB

        Mockito.when(branchRepositoryMock.findById(carDto.getBranchId())).thenReturn(Optional.empty());

        // given: CarDto with incorrect branchId

        // when: createCar(CarDto)

        carService.createCar(carDto);

        // then: exception thrown

    }

    @Test
    public void shouldReturnCarDtoWithGeneratedIdWhenCarDtoHasCorrectData() {

        // mock everything and use the "times" mockito method to make sure code reaches carRepository.save() once

        Mockito.when(carRepositoryMock.findByVin("2T1BURHE4EC055131")).thenReturn(null); // = vin not yet in DB

        BranchEntity branchEntity = buildBranchEntity(2L);
        CarDto carDto = buildCarDto(1L, "2T1BURHE4EC055131", branchEntity, buildEquipment());
        CarEntity carEntity = buildCarEntity(carDto);

        Mockito.when(carMapperMock.toCarEntity(Mockito.any(CarDto.class))).thenReturn(carEntity); // excluding mapper from test

        Mockito.when(branchRepositoryMock.findById(carDto.getBranchId())).thenReturn(Optional.of(branchEntity)); // = such branch exists in DB

        Mockito.when(equipmentRepositoryMock.findById(4L)).thenReturn(Optional.of(carDto.getEquipment().get(0)));
        Mockito.when(equipmentRepositoryMock.findById(7L)).thenReturn(Optional.of(carDto.getEquipment().get(1)));
        Mockito.when(equipmentRepositoryMock.findById(2L)).thenReturn(Optional.of(carDto.getEquipment().get(2)));

        Mockito.when(carRepositoryMock.save(Mockito.any(CarEntity.class))).thenReturn(carEntity);

        Mockito.when(carMapperMock.toCarDto(Mockito.any(CarEntity.class))).thenReturn(carDto);

        // given: proper CarDto

        // when: createCar(carDto)

        // Can't use times() on real object, so we make a spy in place of carService
        CarService carServiceSpy = Mockito.spy(new CarService(carRepositoryMock, branchRepositoryMock, carMapperMock, equipmentRepositoryMock));
        carServiceSpy.createCar(carDto);

        // then: reaches carRepository(carEntity)

        Mockito.verify(carServiceSpy, times(1)).createCar(carDto); // method was reached = all carService code was executed

    }

    private CarEntity buildCarEntity(CarDto carDto) {
        BranchEntity branchEntity = buildBranchEntity(carDto.getBranchId());
        return CarEntity.builder()
                .id(carDto.getCarId())
                .vin(carDto.getVin())
                .make(carDto.getMake())
                .model(carDto.getModel())
                .bodyType(carDto.getBodyType())
                .year(carDto.getYear())
                .colour(carDto.getColour())
                .mileage(carDto.getMileage())
                .status(carDto.getStatus())
                .dailyRate(carDto.getDailyRate())
                .engineType(carDto.getEngineType())
                .engineCapacity(carDto.getEngineCapacity())
                .segment(carDto.getSegment())
                .transmission(carDto.getTransmission())
                .seats(carDto.getSeats())
                .doors(carDto.getDoors())
                .branch(branchEntity)
                .equipment(carDto.getEquipment())
//                .photoUrl(fromCarDto.getPhotoUrl()) - not sure how to store media, fix later
                .build();
    }

    private CarDto buildCarDto(Long id, String vin, BranchEntity branchEntity, List<EquipmentEntity> equipment) {
        return CarDto.builder()
                .carId(id)
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
                .seats(4)
                .doors(5)
                .branchId(branchEntity.getId())
                .equipment(equipment)
                //.photoUrl("https://fakeimage.pl")
                .build();
    }

    private BranchEntity buildBranchEntity(Long id) {
        return BranchEntity.builder()
                .id(id)
                .address(new AddressBranchEntity())
                .status(BranchStatusEnum.OPENED)
                .mainOffice(new MainOfficeEntity())
                .build();
    }

    private List<EquipmentEntity> buildEquipment() {
        List<EquipmentEntity> equipment = new ArrayList<>();
        EquipmentEntity e1 = new EquipmentEntity(4L, "Air Conditioning");
        EquipmentEntity e2 = new EquipmentEntity(7L, "GPS navigation");
        EquipmentEntity e3 = new EquipmentEntity(2L, "Internet Access");
        equipment.add(e1);
        equipment.add(e2);
        equipment.add(e3);
        return equipment;
    }

}