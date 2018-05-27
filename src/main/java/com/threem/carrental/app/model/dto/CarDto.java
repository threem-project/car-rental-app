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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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

    @NotBlank
    @Size(min = 17, max = 17, message = "VIN must have 17 characters")
    @Pattern(regexp = "/^(?<wmi>[A-HJ-NPR-Z\\d]{3})(?<vds>[A-HJ-NPR-Z\\d]{5})(?<check>[\\dX])(?<vis>(?<year>[A-HJ-NPR-Z\\d])(?<plant>[A-HJ-NPR-Z\\d])(?<seq>[A-HJ-NPR-Z\\d]{6}))$/")
    private String vin;

    @NotBlank
    @Size(min = 3, max = 100, message = "Make must be between 3 and 100 characters long.")
    private String make;

    @NotBlank
    @Size(min = 3, max = 100, message = "Model must be between 3 and 100 characters long.")
    private String model;

    @NotBlank
    @Size(min = 3, max = 100, message = "Body type must be between 3 and 100 characters long.")
    private CarBodyTypeEnum bodyType;

    @NotBlank
    @Size(min = 3, max = 100, message = "Production year must be between 3 and 100 characters long.")
    private String year;

    @NotBlank
    @Size(min = 3, max = 100, message = "Colour must be between 3 and 100 characters long.")
    private CarColourEnum colour;

    @NotBlank
    private Integer mileage;

    @NotBlank
    @Size(min = 3, max = 100)
    private CarStatusEnum status;

    @NotBlank
    @Digits(integer = 6, fraction = 2, message = "Format is: XXXX.XX")
    private BigDecimal dailyRate;

    @NotBlank
    @Size(min = 3, max = 45, message = "Engine type must be between 3 and 45 characters long.")
    private CarEngineTypeEnum engineType;

    @NotBlank
    private Integer engineCapacity;

    @NotBlank
    @Size(min = 3, max = 45, message = "Engine type must be between 3 and 45 characters long.")
    private CarSegmentTypeEnum segment;

    @NotBlank
    @Size(min = 3, max = 45, message = "Engine type must be between 3 and 45 characters long.")
    private CarTransmissionTypeEnum transmission;

    @NotBlank
    private Integer seats;

    @NotBlank
    private Integer doors;

    private BranchEntity branch;

    private List<EquipmentEntity> equipment;

    private String photoUrl;

}
