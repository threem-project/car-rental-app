package com.threem.carrental.app.service.mapper;

import com.threem.carrental.app.model.dto.CarDto;
import com.threem.carrental.app.model.entity.BranchEntity;
import com.threem.carrental.app.model.entity.CarEntity;
import com.threem.carrental.app.model.entity.EquipmentEntity;
import com.threem.carrental.app.repository.EquipmentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marika Grzebieniowska on 27.05.2018
 * @project car-rental-app
 */

@Service
public class CarMapper {

    public CarEntity toCarEntity(CarDto carDto) {

        BranchEntity dummyBranchEntity = new BranchEntity();
        if (carDto.getBranchId() != null) {
            dummyBranchEntity.setId(carDto.getBranchId());
        }

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
                .branch(dummyBranchEntity) // dummy branch with only branchId
                .equipment(carDto.getEquipment())
//                .photoUrl(fromCarDto.getPhotoUrl()) - not sure how to store media, fix later
                .build();
    }

    public CarDto toCarDto(CarEntity carEntity) {

        return CarDto.builder()
                .carId(carEntity.getId())
                .vin(carEntity.getVin())
                .make(carEntity.getMake())
                .model(carEntity.getModel())
                .bodyType(carEntity.getBodyType())
                .year(carEntity.getYear())
                .colour(carEntity.getColour())
                .mileage(carEntity.getMileage())
                .status(carEntity.getStatus())
                .dailyRate(carEntity.getDailyRate())
                .engineType(carEntity.getEngineType())
                .engineCapacity(carEntity.getEngineCapacity())
                .segment(carEntity.getSegment())
                .transmission(carEntity.getTransmission())
                .seats(carEntity.getSeats())
                .doors(carEntity.getDoors())
                .branchId(carEntity.getBranch().getId())
                .equipment(carEntity.getEquipment())
                .build();
    }

    // TODO maper w pewnych warunkach nie działa poprawnie, brakuje przypadków testowych
}
