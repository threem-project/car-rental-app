package com.threem.carrental.app.service;

import com.threem.carrental.app.errorHandler.customExceptions.IncorrectBranchException;
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
        Optional<CarDto> result = Optional.empty();
        CarEntity carEntity = carMapper.toCarEntity(carDto);

        if (carEntity.getBranch().getId() != null) {
            Optional<BranchEntity> branchEntityInDb = branchRepository.findById(carDto.getBranchId());
            if (branchEntityInDb.isPresent()) {
                carEntity.setBranch(branchEntityInDb.get());
            } else {
                throw new IncorrectBranchException("Given branch ID is incorrect");
            }
        } else {
            carEntity.setBranch(null);  // frontend will disallow adding cars with no branchId
        }

        CarEntity savedCarEntity = carRepository.save(carEntity);
        result = Optional.of(carMapper.toCarDto(savedCarEntity));
        return result;
    }
}
