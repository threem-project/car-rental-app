package com.threem.carrental.factory;

import com.threem.carrental.app.model.entity.CarEntity;
import com.threem.carrental.app.model.entity.enumTypes.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author marek_j on 2018-06-24
 */
public class TestCarEntityFactory {

    private static List<String> allowedParameters = Arrays.asList("WITH_ALL_FIELDS_NULL_EXCEPT_VIN",
                                                                    "FORD_RANDOM_VIN_4_SEATS_4_DOORS_4000_CCM_NO_EQUIPMENT_AUTOMATIC",
                                                                    "SKODA_RANDOM_VIN_3_SEATS_3_DOORS_3000_CCM_NO_EQUIPMENT_MANUAL");


    public static CarEntity getEntity(String carType) {

        String switchParameter = carType.toUpperCase();

        switch (switchParameter) {
            case "WITH_ALL_FIELDS_NULL_EXCEPT_VIN":
                return CarEntity.builder()
                        .id(null)
                        .vin(DummyVinGenerator.generateVin())
                        .make(null)
                        .model(null)
                        .segment(null)
                        .seats(null)
                        .doors(null)
                        .bodyType(null)
                        .colour(null)
                        .engineType(null)
                        .engineCapacity(null)
                        .transmission(null)
                        .year(null)
                        .dailyRate(null)
                        .equipment(null)
                        .mileage(null)
                        .status(null)
                        .branch(null)
                        .build();

            case "FORD_RANDOM_VIN_4_SEATS_4_DOORS_4000_CCM_NO_EQUIPMENT_AUTOMATIC":
                return CarEntity.builder()
                    .id(null)
                    .vin(DummyVinGenerator.generateVin())
                    .make("Ford")
                    .model("TestMondeo")
                    .segment(CarSegmentTypeEnum.D_LARGE)
                    .seats(4)
                    .doors(4)
                    .bodyType(CarBodyTypeEnum.SEDAN)
                    .colour(CarColourEnum.WHITE)
                    .engineType(CarEngineTypeEnum.PETROL)
                    .engineCapacity(4000)
                    .transmission(CarTransmissionTypeEnum.AUTOMATIC)
                    .year(2010)
                    .dailyRate(new BigDecimal(5.00))
                    .equipment(null)
                    .mileage(666)
                    .status(CarStatusEnum.AVAILABLE)
                    .branch(null)
                    .build();

            case "SKODA_RANDOM_VIN_3_SEATS_3_DOORS_3000_CCM_NO_EQUIPMENT_MANUAL":
                return CarEntity.builder()
                    .id(null)
                    .vin(DummyVinGenerator.generateVin())
                    .make("Skoda")
                    .model("TestOctavia")
                    .segment(CarSegmentTypeEnum.C_MEDIUM)
                    .seats(4)
                    .doors(4)
                    .bodyType(CarBodyTypeEnum.HATCHBACK)
                    .colour(CarColourEnum.RED)
                    .engineType(CarEngineTypeEnum.DIESEL)
                    .engineCapacity(3000)
                    .transmission(CarTransmissionTypeEnum.MANUAL)
                    .year(1960)
                    .dailyRate(new BigDecimal(20.00))
                    .equipment(null)
                    .mileage(10000)
                    .status(CarStatusEnum.IN_USE)
                    .branch(null)
                    .build();

            default:
                throw new IllegalArgumentException("Parameters allowed for " + TestCarEntityFactory.class.getName() + " are: " + allowedParameters);
        }
    }


}
