package com.threem.carrental.app.controller;

import com.threem.carrental.app.model.dto.CarSearchDto;
import com.threem.carrental.app.model.entity.CarEntity;
import com.threem.carrental.app.service.CarService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author marek_j on 2018-06-20 based on code from Marika Grzebieniowska on 27.05.2018
 */
@RestController
@RequestMapping("car")
public class CarController {

    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping (produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<CarEntity> createCar(@Validated @RequestBody CarEntity carEntity) {
        Optional<CarEntity> carEntityFromService = carService.createCar(carEntity);
        if (carEntityFromService.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(carEntityFromService.get());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping (produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<CarEntity> updateCar(@Validated @RequestBody CarEntity carEntity) {
        Optional<CarEntity> carEntityFromService = carService.updateCar(carEntity);
        if (carEntityFromService.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(carEntityFromService.get());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping (value = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<CarEntity> findById(@PathVariable Long id) {
        Optional<CarEntity> carEntityFromService = carService.findById(id);
        if (carEntityFromService.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(carEntityFromService.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @GetMapping (produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Page<CarEntity>> findAllPaginated(@RequestParam(defaultValue = "0") Integer currentPage,
                                                            @RequestParam(defaultValue = "20") Integer resultsPerPage) {
        Page<CarEntity> carEntityPage = carService.findAllPaginated(currentPage,resultsPerPage);
        return ResponseEntity.status(HttpStatus.OK).body(carEntityPage);
    }

    @PostMapping (value = "find", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<CarEntity>> findByCarSearchDto(@RequestBody CarSearchDto carSearchDto) {
        List<CarEntity> carEntityList = carService.findByCarSearchDto(carSearchDto);
        if (carEntityList.size()==0 || carEntityList==null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(carEntityList);
    }

}
