package com.threem.carrental.app.service;

import com.threem.carrental.app.errorHandler.customExceptions.CarAlreadyExistsException;
import com.threem.carrental.app.errorHandler.customExceptions.IncorrectBranchException;
import com.threem.carrental.app.model.dto.CarDto;
import com.threem.carrental.app.model.entity.BranchEntity;
import com.threem.carrental.app.model.entity.CarEntity;
import com.threem.carrental.app.model.entity.EquipmentEntity;
import com.threem.carrental.app.repository.BranchRepository;
import com.threem.carrental.app.repository.CarRepository;
import com.threem.carrental.app.repository.EquipmentRepository;
import com.threem.carrental.app.service.mapper.CarMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Marika Grzebieniowska on 27.05.2018
 * @project car-rental-app
 */

@Service
public class CarService {

    private CarRepository carRepository;
    private BranchRepository branchRepository;
    private CarMapper carMapper;
    private EquipmentRepository equipmentRepository;

    public CarService(CarRepository carRepository, BranchRepository branchRepository, CarMapper carMapper, EquipmentRepository equipmentRepository) {
        this.carRepository = carRepository;
        this.branchRepository = branchRepository;
        this.carMapper = carMapper;
        this.equipmentRepository = equipmentRepository;
    }

    public Optional<CarDto> createCar(CarDto carDto) {

        if (carRepository.findByVin(carDto.getVin()) != null) {
            throw new CarAlreadyExistsException("Car with this vin number is already in DB");
        }

        CarEntity carEntity = carMapper.toCarEntity(carDto);

        setBranchUsingId(carEntity, carDto);
        setEquipment(carEntity, carDto);

        CarEntity savedCarEntity = carRepository.save(carEntity);
        return Optional.of(carMapper.toCarDto(savedCarEntity));
    }

    private void setBranchUsingId(CarEntity carEntity, CarDto carDto) {
        if (carEntity.getBranch().getId() != null) {
            Optional<BranchEntity> branchEntityInDb = branchRepository.findById(carDto.getBranchId());
            branchEntityInDb.orElseThrow(() -> new IncorrectBranchException("Given branch ID is incorrect"));
        }
    }

    private void setEquipment(CarEntity carEntity, CarDto carDto) {
        if (carDto.getEquipment() != null) {
            carEntity.setEquipment(mapToEntitiesFromDb(carDto));
        }
    }

    private List<EquipmentEntity> mapToEntitiesFromDb(CarDto carDto) {
        return carDto.getEquipment()
                .stream()
                .map(e -> equipmentRepository.findById(e.getId()).get())
                .collect(Collectors.toList()
                );
    }

}






