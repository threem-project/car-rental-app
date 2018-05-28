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

    public Optional<CarDto> createCar(CarDto carDto) {
        Optional<CarDto> resultCarDto = Optional.empty();
        CarEntity carEntity = carMapper.toCarEntity(carDto);

        Optional<BranchEntity> branchEntityInDb = branchRepository.findById(carEntity.getBranch().getId());
        if (branchEntityInDb.isPresent()) {
            carEntity.setBranch(branchEntityInDb.get());
        }

        carRepository.save(carEntity);
        // TODO if Validation failed, catch ConstraintViolationException and handle in GeneralControllerAdvisor

        // TODO: should rather be looking by vin, no? Why doesn't findByVin() work? Make it key?
        Optional<CarEntity> carEntityFromDb = carRepository.findById(carEntity.getId());
        if (carEntityFromDb.isPresent()) {
            CarDto mappedCarDto = carMapper.toCarDto(carEntityFromDb.get());
            resultCarDto = Optional.of(mappedCarDto);
        }
        return resultCarDto;
    }
}