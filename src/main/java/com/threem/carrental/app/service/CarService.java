package com.threem.carrental.app.service;

import com.threem.carrental.app.model.dto.CarDto;
import com.threem.carrental.app.model.entity.BranchEntity;
import com.threem.carrental.app.model.entity.CarEntity;
import com.threem.carrental.app.repository.BranchRepository;
import com.threem.carrental.app.repository.CarRepository;
import com.threem.carrental.app.service.mapper.CarMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Marika Grzebieniowska on 27.05.2018
 * @project car-rental-app
 */

@Service
public class CarService {

    private CarRepository carRepository;
    private BranchRepository branchRepository;
    private CarMapper carMapper;

    public CarService(CarRepository carRepository, BranchRepository branchRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.branchRepository = branchRepository;
        this.carMapper = carMapper;
    }

// przy mapowaniu traktowali BranchEntity w EmployeeEntity jedynie jako nośnik informacji o numerze branchId.
// Samo sprawdzenie/wyciągnięcie/nadpisanie prawidłowego BranchEntity w EmployeeEntity robimy dopiero w Service
    public Optional<CarDto> createCar(CarDto carDto) {
        Optional<CarDto> resultCarDto = Optional.empty();
        CarEntity carEntity = carMapper.toCarEntity(carDto);

        // At this point BranchEntity in carEntity is a dummy
        // If branchrepository has a branch with branchId like in dummy -> get and overwrite (car update)
        Optional<BranchEntity> branchEntityInDb = branchRepository.findById(carEntity.getBranch().getId());
        if (branchEntityInDb.isPresent()) {
            carEntity.setBranch(branchEntityInDb.get());
        }

        carRepository.save(carEntity);
        // TODO catch ConstraintViolationException and handle in GeneralControllerAdvisor

        Optional<CarEntity> carEntityFromDb = carRepository.findById(carEntity.getId());
        if (carEntityFromDb.isPresent()) {
            CarDto mappedCarDto = carMapper.toCarDto(carEntityFromDb.get());
            resultCarDto = Optional.of(mappedCarDto);
        }
        return resultCarDto;
    }
}