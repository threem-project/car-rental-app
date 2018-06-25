package com.threem.carrental.factory;

import com.threem.carrental.app.model.entity.enumTypes.CarEquipmentEnum;

import java.util.Arrays;
import java.util.List;

/**
 * @author marek_j on 2018-06-24
 */
public class TestCarEquipmentFactory {

    private static List<String> allowedParameters = Arrays.asList("EMPTY_LIST",
                                                                  "AIR_CONDITIONING",
                                                                  "AIR_CONDITIONING_GPS");

    public static List<CarEquipmentEnum> getEquipment(String equipmentBundle) {

        String switchParameter = equipmentBundle.toUpperCase();

        switch (switchParameter) {
            case "EMPTY_LIST":
                return Arrays.asList();

            case "AIR_CONDITIONING":
                return Arrays.asList(CarEquipmentEnum.AIR_CONDITIONING);

            case "AIR_CONDITIONING_GPS":
                return Arrays.asList(CarEquipmentEnum.AIR_CONDITIONING,
                                     CarEquipmentEnum.NAVIGATION);

            default:
                throw new IllegalArgumentException("Parameters allowed for " + TestCarEquipmentFactory.class.getName() + " are: " + allowedParameters);
        }




    }

}
