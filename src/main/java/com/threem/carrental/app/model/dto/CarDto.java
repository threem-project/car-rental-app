package com.threem.carrental.app.model.dto;

import com.threem.carrental.app.model.entity.EquipmentEntity;
import com.threem.carrental.app.model.entity.enumTypes.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Marika Grzebieniowska on 27.05.2018
 * @project car-rental-app
 */

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CarDto {

    private Long carId;

    // TODO fix regex or write validator for vin using control number https://pl.wikipedia.org/wiki/Vehicle_Identification_Number
    @NotBlank
    @Size(min = 17, max = 17, message = "VIN must have 17 characters")
//    @Pattern(regexp = "/^([A-HJ-NPR-Z\\d]{3})([A-HJ-NPR-Z\\d]{5})([\\dX])(([A-HJ-NPR-Z\\d])([A-HJ-NPR-Z\\d])([A-HJ-NPR-Z\\d]{6}))$/i")
    private String vin;

    @NotBlank
    @Size(min = 3, max = 100, message = "Make must be between 3 and 100 characters long.")
    private String make;

    @NotBlank
    @Size(min = 3, max = 100, message = "Model must be between 3 and 100 characters long.")
    private String model;

    private CarBodyTypeEnum bodyType;

    @NotBlank
    @Size(min = 3, max = 100, message = "Production year must be between 3 and 100 characters long.")
    private String year;

    private CarColourEnum colour;

    @NotNull
    private Integer mileage;

    private CarStatusEnum status;

    @NotNull
    @Digits(integer = 6, fraction = 2, message = "Format is: XXXX.XX")
    private BigDecimal dailyRate;

    private CarEngineTypeEnum engineType;

    @NotNull
    private Integer engineCapacity;

    private CarSegmentTypeEnum segment;

    private CarTransmissionTypeEnum transmission;

    @NotNull
    private Integer seats;

    @NotNull
    private Integer doors;

    private Long branchId;

    private List<EquipmentEntity> equipment;

//    private String photoUrl;

}
