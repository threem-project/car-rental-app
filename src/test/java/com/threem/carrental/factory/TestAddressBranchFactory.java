package com.threem.carrental.factory;

import com.threem.carrental.app.model.entity.AddressBranchEntity;

import java.util.Arrays;
import java.util.List;

/**
 * @author marek_j on 2018-06-15
 */
public class TestAddressBranchFactory {


    private static List<String> allowedParameters = Arrays.asList("MIASTO_TESTOWE");

    public static AddressBranchEntity getEntity(String adressType) {

        String switchParameter = adressType.toUpperCase();

        switch (switchParameter) {
            case "MIASTO_TESTOWE":
                return AddressBranchEntity.builder()
                        .city("miastoTestowe")
                        .build();

            default:
                throw new IllegalArgumentException("Parameters allowed for " + TestAddressBranchFactory.class.getName() + " are: " + allowedParameters);

        }
    }

}
