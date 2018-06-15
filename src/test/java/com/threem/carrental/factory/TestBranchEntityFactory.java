package com.threem.carrental.factory;

import com.threem.carrental.app.model.entity.BranchEntity;
import com.threem.carrental.app.model.entity.enumTypes.BranchStatusEnum;

import java.util.Arrays;
import java.util.List;

/**
 * @author marek_j on 2018-06-15
 */
public class TestBranchEntityFactory {

    private static List<String> allowedParameters = Arrays.asList("OPEN_BRANCH");

    public static BranchEntity getEntity(String branchType) {

        String switchParameter = branchType.toUpperCase();

        switch (switchParameter) {
            case "OPEN_BRANCH":
                return BranchEntity.builder()
                        .id(null)
                        .status(BranchStatusEnum.OPEN)
                        .build();

            default:
                throw new IllegalArgumentException("Parameters allowed for " + TestBranchEntityFactory.class.getName() + " are: " + allowedParameters);

        }
    }
}
