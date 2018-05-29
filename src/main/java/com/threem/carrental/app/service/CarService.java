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
        CarEntity carEntity = carMapper.toCarEntity(carDto);

        Optional<BranchEntity> branchEntityInDb = branchRepository.findById(carDto.getBranchId());
        if (branchEntityInDb.isPresent()) {
            carEntity.setBranch(branchEntityInDb.get());
        }

        CarEntity carEntityFromDb = carRepository.save(carEntity);
        // TODO catch exception if save() fails
        // TODO catch ConstraintViolationException if validation fails

        Optional<CarDto> resultCarDto;
        resultCarDto = Optional.of(carMapper.toCarDto(carEntityFromDb));
        return resultCarDto;
    }

}
