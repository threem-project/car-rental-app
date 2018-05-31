package com.threem.carrental.app.service.mapper;

import com.threem.carrental.app.model.dto.CarDto;
import com.threem.carrental.app.model.entity.BranchEntity;
import com.threem.carrental.app.model.entity.CarEntity;
import org.springframework.stereotype.Service;

/**
 * @author Marika Grzebieniowska on 27.05.2018
 * @project car-rental-app
 */

@Service
public class CarMapper {

    public CarEntity toCarEntity(CarDto fromCarDto) {
        BranchEntity dummyBranchEntity = new BranchEntity();
        if (fromCarDto.getBranchId() != null) {
            dummyBranchEntity.setId(fromCarDto.getBranchId());
        }

        CarEntity carEntity = CarEntity.builder()
                .id(fromCarDto.getCarId())
                .vin(fromCarDto.getVin())
                .make(fromCarDto.getMake())
                .model(fromCarDto.getModel())
                .bodyType(fromCarDto.getBodyType())
                .year(fromCarDto.getYear())
                .colour(fromCarDto.getColour())
                .mileage(fromCarDto.getMileage())
                .status(fromCarDto.getStatus())
                .dailyRate(fromCarDto.getDailyRate())
                .engineType(fromCarDto.getEngineType())
                .engineCapacity(fromCarDto.getEngineCapacity())
                .segment(fromCarDto.getSegment())
                .transmission(fromCarDto.getTransmission())
                .seats(fromCarDto.getSeats())
                .doors(fromCarDto.getDoors())
                .branch(dummyBranchEntity) // dummy branch with only branchId
                .equipment(fromCarDto.getEquipment())
//                .photoUrl(fromCarDto.getPhotoUrl()) - not sure how to store media, fix later
                .build();
        return carEntity;
    }

    public CarDto toCarDto(CarEntity carEntity) {

        CarDto carDto = CarDto.builder()
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
        return carDto;
    }

    // TODO maper w pewnych warunkach nie działa poprawnie, brakuje przypadków testowych
}
