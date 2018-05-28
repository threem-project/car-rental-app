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
        CarEntity carEntity = new CarEntity().builder()
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
                .branch(new BranchEntity()) // dummy branch
//                .equipment() TODO dummy branch
//                .photoUrl(fromCarDto.getPhotoUrl()) TODO figure this out
                .build();
        return carEntity;
    }

    public CarDto toCarDto(CarEntity carEntity) {
        CarDto carDto = new CarDto().builder()
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
                .branch(carEntity.getBranch())
                .equipment(carEntity.getEquipment())
                // TODO photo url?
                .build();
        return carDto;
    }

}
