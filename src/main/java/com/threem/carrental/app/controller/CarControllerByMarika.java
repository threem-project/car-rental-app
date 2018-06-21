package com.threem.carrental.app.controller;

import com.threem.carrental.app.model.dto.CarDto;
import com.threem.carrental.app.service.CarServiceByMarika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author Marika Grzebieniowska on 27.05.2018
 * @project car-rental-app
 */

@Deprecated
@RestController
@RequestMapping("carByMarika")
public class CarControllerByMarika {

//    private final CarServiceByMarika carServiceByMarika;
//
//    @Autowired
//    public CarControllerByMarika(CarServiceByMarika carServiceByMarika) {
//        this.carServiceByMarika = carServiceByMarika;
//    }
//
//    @PostMapping
//    public ResponseEntity<CarDto> createCar(@Validated @RequestBody CarDto carDto) {
//        Optional<CarDto> carDtoFromService = carServiceByMarika.createCar(carDto);
//        if (carDtoFromService.isPresent()) {
//            return ResponseEntity.status(HttpStatus.CREATED).body(carDtoFromService.get());
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // TODO other responses in exception handling depending on the fault
//        }
//    }
}
