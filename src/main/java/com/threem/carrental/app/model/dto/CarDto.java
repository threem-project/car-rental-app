package com.threem.carrental.app.model.dto;

import com.threem.carrental.app.model.entity.BranchEntity;
import com.threem.carrental.app.model.entity.EquipmentEntity;
import com.threem.carrental.app.model.entity.enumTypes.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CarDto {

    private Long carId;

    @NotBlank
    @Size(min = 3, max = 100)
    private CarLabelEnum label;

    @NotBlank
    @Size(min = 3, max = 100)
    private CarModelEnum model;

    @NotBlank
    @Size(min = 3, max = 100)
    private CarBodyTypeEnum bodyType;

    @NotBlank
    @Size(min = 3, max = 100)
    private String year;

    @NotBlank
    @Size(min = 3, max = 100)
    private CarColourEnum colour;

    @NotBlank
    private Integer mileage;

    @NotBlank
    @Size(min = 3, max = 100)
    private CarStatusEnum status;

    @NotBlank
    @Digits(integer = 6, fraction = 2)
    private BigDecimal dailyRate;

    @NotBlank
    @Size(min = 3, max = 45)
    private CarEngineTypeEnum engineType;

    @NotBlank
    private Integer engineCapacity;

    @NotBlank
    @Size(min = 3, max = 45)
    private CarSegmentTypeEnum segment;

    @NotBlank
    @Size(min = 3, max = 45)
    private CarTransmissionTypeEnum transmission;

    @NotBlank
    private Integer seats;

    @NotBlank
    private Integer doors;

    private BranchEntity branch;

    private List<EquipmentEntity> equipment;

}
