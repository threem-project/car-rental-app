package com.threem.carrental.app.service;

import com.threem.carrental.app.errorHandler.customExceptions.CarAlreadyExistsException;
import com.threem.carrental.app.errorHandler.customExceptions.CarIdAndVinDoNotMatch;
import com.threem.carrental.app.model.entity.CarEntity;
import com.threem.carrental.app.repository.CarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author marek_j on 2018-06-20 based on original code by Marika Grzebieniowska on 27.05.2018
 */
@Service
public class CarService {

    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
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
        if (!vinAndIdMatchTheSameEntity(givenEntity)) {
            throw new CarIdAndVinDoNotMatch("This ID and VIN are assigned to different entities");
        }

        carRepository.save(givenEntity);
        return Optional.of(givenEntity);
    }

    private Boolean vinAndIdMatchTheSameEntity(CarEntity carEntity) {
        Long id = carEntity.getId();
        String vin = carEntity.getVin();
        CarEntity entityInDb = carRepository.findByIdAndVin(id,vin);
        return (entityInDb!=null);
    }

    public Optional<CarEntity> findById(Long id) {
        Optional<CarEntity> resultEntity = carRepository.findById(id);
        return resultEntity;
    }
}
