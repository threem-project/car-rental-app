package com.threem.carrental.app.service;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.threem.carrental.app.errorHandler.customExceptions.CarAlreadyExistsException;
import com.threem.carrental.app.errorHandler.customExceptions.CarDoesNotExistsException;
import com.threem.carrental.app.model.dto.CarSearchDto;
import com.threem.carrental.app.model.entity.CarEntity;
import com.threem.carrental.app.repository.CarRepository;
import com.threem.carrental.app.repository.EquipmentRepository;
import com.threem.carrental.app.repository.expressionBuilder.QCarExpressionBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author marek_j on 2018-06-20 based on original code by Marika Grzebieniowska on 27.05.2018
 */
@Service
public class CarService {

    private CarRepository carRepository;
    private EquipmentRepository equipmentRepository;

    public CarService(CarRepository carRepository, EquipmentRepository equipmentRepository) {
        this.carRepository = carRepository;
        this.equipmentRepository = equipmentRepository;
    }

    @Transactional
    public Optional<CarEntity> createCar(CarEntity givenEntity) {

        if (entityExistInDb(givenEntity)) {
            throw new CarAlreadyExistsException("Car with this VIN number or ID is already in DB");
        }
        carRepository.save(givenEntity);

        return Optional.of(givenEntity);
    }

    private Boolean entityExistInDb(CarEntity carEntity) {
        if (idExistInDb(carEntity) || vinExistInDb(carEntity)) {
            return true;
        }
        return false;
    }

    private Boolean idExistInDb(CarEntity givenEntity) {
        Boolean result = false;
        if (givenEntity.getId()!=null) {
            Optional<CarEntity> carInDb = carRepository.findById(givenEntity.getId());
            if (carInDb.isPresent()) {
                result = true;
            }
        }
        return result;
    }

    private Boolean vinExistInDb(CarEntity carEntity) {
        return carRepository.findByVin(carEntity.getVin())!=null;
    }

    @Transactional
    public Optional<CarEntity> updateCar(CarEntity givenEntity) {

        if (!idExistInDb(givenEntity)) {
            throw new CarDoesNotExistsException("Car with this ID does not exist in DB");
        }

        nullEquipmentOfEntityInDb(givenEntity);
        carRepository.save(givenEntity);
        return Optional.of(givenEntity);
    }

    private void nullEquipmentOfEntityInDb(CarEntity givenEntity) {
        Long idOfEntityToClear = givenEntity.getId();
        String vinOfEntityToClear = givenEntity.getVin();
        CarEntity entityInDb = carRepository.findByIdAndVin(idOfEntityToClear,vinOfEntityToClear);

        if (entityInDb!=null) {
            entityInDb.setEquipment(null);
        }
    }

    public Optional<CarEntity> findById(Long id) {
        Optional<CarEntity> resultEntity = carRepository.findById(id);
        return resultEntity;
    }

    public Page<CarEntity> findAllPaginated(Integer pageNumber, Integer elementsPerPage) {
        PageRequest pageableRequest = PageRequest.of(pageNumber, elementsPerPage);
        Page<CarEntity> employeePage = carRepository.findAll(pageableRequest);
        return employeePage;
    }

    public List<CarEntity> findByCarSearchDto(CarSearchDto carSearchDto) {
        CarEntity carSearchEntity = carSearchDto.getCarEntity();
        QCarExpressionBuilder builder = new QCarExpressionBuilder.Builder() //todo refactor builder to have constructor with dto
                .id(carSearchEntity.getId())
                .vin(carSearchEntity.getVin())
                .make(carSearchEntity.getMake())
                .model(carSearchEntity.getModel())
                .bodyType(carSearchEntity.getBodyType())
                .yearExact(carSearchEntity.getYear())
                .yearBetween(carSearchDto.getYearFrom(),carSearchDto.getYearTo())
                .capacityExact(carSearchEntity.getEngineCapacity())
                .capacityBetween(carSearchDto.getCapacityFrom(),carSearchDto.getCapacityTo())
                .seatsExact(carSearchEntity.getSeats())
                .seatsBetween(carSearchDto.getSeatsFrom(),carSearchDto.getSeatsTo())
                .doorsExact(carSearchEntity.getDoors())
                .doorsBetween(carSearchDto.getDoorsFrom(),carSearchDto.getDoorsTo())
                .dailyRateExact(carSearchEntity.getDailyRate())
                .dailyRateBetween(carSearchDto.getDailyRateFrom(),carSearchDto.getDailyRateTo())
                .colour(carSearchEntity.getColour())
                .status(carSearchEntity.getStatus())
                .engineType(carSearchEntity.getEngineType())
                .segment(carSearchEntity.getSegment())
                .equipment(carSearchEntity.getEquipment())
                .addressCity(carSearchEntity.getBranch().getAddress())
                .build();

        List<CarEntity> carsList = new ArrayList<>();
        if (builder.hasExpression()) {
            BooleanExpression booleanExpression = builder.getExpression();
            Iterable<CarEntity> carEntities = carRepository.findAll(booleanExpression);
            carEntities.forEach(e -> carsList.add(e));
        }
        return carsList;
    }
}
