package com.threem.carrental.testEntitiesFactory;

import com.threem.carrental.app.model.entity.EquipmentEntity;

import java.util.Arrays;
import java.util.List;

/**
 * @author marek_j on 2018-06-24
 */
public class TestCarEquipmentFactory {

    private static List<String> allowedParameters = Arrays.asList("EMPTY_LIST",
                                                                  "TEST_AIR_CONDITIONING",
                                                                  "TEST_AIR_CONDITIONING_GPS");

    public static List<EquipmentEntity> getEquipment(String equipmentBundle) {

        String switchParameter = equipmentBundle.toUpperCase();

        switch (switchParameter) {
            case "EMPTY_LIST":
                return Arrays.asList();

            case "TEST_AIR_CONDITIONING":
                return Arrays.asList(new EquipmentEntity(null,"TEST_AIR_CONDITIONING"));

            case "TEST_AIR_CONDITIONING_GPS":
                return Arrays.asList(new EquipmentEntity(null,"TEST_AIR_CONDITIONING"),
                                     new EquipmentEntity(null,"TEST_GPS"));

            default:
                throw new IllegalArgumentException("Parameters allowed for " + TestCarEquipmentFactory.class.getName() + " are: " + allowedParameters);
        }




    }

}
