package com.threem.carrental.app.service;

import com.threem.carrental.app.model.dto.CarDto;
import com.threem.carrental.app.model.entity.CarEntity;
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
    private CarMapper carMapper;

    public CarService(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    public Optional<CarDto> createCar(CarDto carDto) {
        Optional<CarDto> resultCarDto = Optional.empty();
        CarEntity carEntity = carMapper.toCarEntity(carDto);

        // checks? VIN

        carRepository.save(carEntity);

        // Retrieve car back from DB and return (as confirmation that it was saved)

        return null;
    }

    public Optional<CarDto> findCarById(Integer carId) {
        // TODO
        return null;
    }
}
