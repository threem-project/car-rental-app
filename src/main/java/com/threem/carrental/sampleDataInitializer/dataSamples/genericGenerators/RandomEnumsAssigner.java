package com.threem.carrental.sampleDataInitializer.dataSamples.genericGenerators;

import com.threem.carrental.app.model.entity.enumTypes.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author marek_j on 2018-06-29
 */
public class RandomEnumsAssigner {

    private Random random = new Random();

    public CustomerAccountStatusEnum assignCustomerAccountStatus() {
        List<CustomerAccountStatusEnum> statusEnumList = Arrays.asList(CustomerAccountStatusEnum.ACTIVE,CustomerAccountStatusEnum.NEW);
        Integer index = random.nextInt(statusEnumList.size());
        return statusEnumList.get(index);
    }

    public CustomerTypeEnum assignCustomerStatus() {
        List<CustomerTypeEnum> statusEnumList = Arrays.asList(CustomerTypeEnum.REGULAR,CustomerTypeEnum.CORPORATE);
        Integer index = random.nextInt(statusEnumList.size());
        return statusEnumList.get(index);
    }

    public BranchStatusEnum assignBranchStatus() {
        List<BranchStatusEnum> statusList = Arrays.asList(BranchStatusEnum.OPEN,BranchStatusEnum.CLOSED,BranchStatusEnum.TEMPORARILY_UNAVAILABLE);
        Integer index = random.nextInt(statusList.size());
        return statusList.get(index);
    }

    public EmployeeStatusEnum assignEmployeeStatus() {
        List<EmployeeStatusEnum> statusEnumList = Arrays.asList(EmployeeStatusEnum.ACTIVE,EmployeeStatusEnum.NEW,EmployeeStatusEnum.DEACTIVATED);
        Integer index = random.nextInt(statusEnumList.size());
        return statusEnumList.get(index);
    }

    public EmployeeRoleEnum assignEmployeeRole() {
        List<EmployeeRoleEnum> roleEnumList = Arrays.asList(EmployeeRoleEnum.REGULAR_EMPLOYEE,EmployeeRoleEnum.BRANCH_MANAGER);
        Integer index = random.nextInt(roleEnumList.size());
        return roleEnumList.get(index);
    }

}
