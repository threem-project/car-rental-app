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

        // check if car with this vin already exists
        if (carRepository.findByVin(carDto.getVin()) != null) {
            throw new CarAlreadyExistsException("Car with this vin number is already in DB");
        }

        Optional<CarDto> result = Optional.empty();
        CarEntity carEntity = carMapper.toCarEntity(carDto);

        if (carEntity.getBranch().getId() != null) {
            Optional<BranchEntity> branchEntityInDb = branchRepository.findById(carDto.getBranchId());
            if (branchEntityInDb.isPresent()) {
                carEntity.setBranch(branchEntityInDb.get());
            } else {
                throw new IncorrectBranchException("Given branch ID is incorrect");
            }
        }

        if (carDto.getEquipment() != null) {
            List<EquipmentEntity> equipmentForSaving = new ArrayList<>();
            for (EquipmentEntity equipmentEntity : carDto.getEquipment()) {
                equipmentForSaving.add(equipmentRepository.findById(equipmentEntity.getId()).get());
                carEntity.setEquipment(equipmentForSaving);
            }
        }

        CarEntity savedCarEntity = carRepository.save(carEntity);
        result = Optional.of(carMapper.toCarDto(savedCarEntity));
        return result;
    }

}

