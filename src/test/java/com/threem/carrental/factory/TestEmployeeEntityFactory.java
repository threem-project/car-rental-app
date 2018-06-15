package com.threem.carrental.factory;

import com.threem.carrental.app.model.entity.EmployeeEntity;
import com.threem.carrental.app.model.entity.enumTypes.EmployeeRoleEnum;
import com.threem.carrental.app.model.entity.enumTypes.EmployeeStatusEnum;

import java.util.Arrays;
import java.util.List;

/**
 * @author marek_j on 2018-06-15
 */
public class TestEmployeeEntityFactory {

    private static List<String> allowedParameters = Arrays.asList("JEBEDIASZ_JONES_ACTIVE_OWNER",
                                                                    "JOHN_KOWALSKI_NEW_REGULAR_EMPLOYEE",
                                                                    "TOM_NOWAK_DEACTIVATED_BRANCH_MANAGER",
                                                                    "WITH_ALL_FIELDS_NULL");

    public static EmployeeEntity getEntity(String employeeType) {

        String switchParameter = employeeType.toUpperCase();

        switch (switchParameter) {
            case "JEBEDIASZ_JONES_ACTIVE_OWNER":
                return EmployeeEntity.builder()
                        .id(null)
                        .firstName("Jebediasz")
                        .lastName("Jones")
                        .password("jebediaszJones")
                        .email("jebediasz@jones")
                        .status(EmployeeStatusEnum.ACTIVE)
                        .role(EmployeeRoleEnum.OWNER)
                        .branch(null)
                        .build();

            case "JOHN_KOWALSKI_NEW_REGULAR_EMPLOYEE":
                return EmployeeEntity.builder()
                        .id(null)
                        .firstName("John")
                        .lastName("Kowalski")
                        .password("johnKowalski")
                        .email("john@kowalski")
                        .status(EmployeeStatusEnum.NEW)
                        .role(EmployeeRoleEnum.REGULAR_EMPLOYEE)
                        .branch(null)
                        .build();

            case "TOM_NOWAK_DEACTIVATED_BRANCH_MANAGER":
                return EmployeeEntity.builder()
                        .id(null)
                        .firstName("Tom")
                        .lastName("Nowak")
                        .password("tomNowak")
                        .email("tom@nowak")
                        .status(EmployeeStatusEnum.DEACTIVATED)
                        .role(EmployeeRoleEnum.BRANCH_MANAGER)
                        .branch(null)
                        .build();
            case "WITH_ALL_FIELDS_NULL":
                return EmployeeEntity.builder()
                        .id(null)
                        .build();

            default:
                throw new IllegalArgumentException("Parameters allowed for " + TestEmployeeEntityFactory.class.getName() + " are: " + allowedParameters);
        }

    }
}
