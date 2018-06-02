package com.threem.carrental.sampleDataInitializer.dataSamples;

import com.threem.carrental.app.model.entity.AddressBranchEntity;
import com.threem.carrental.app.model.entity.BranchEntity;
import com.threem.carrental.app.model.entity.MainOfficeEntity;
import com.threem.carrental.app.model.entity.enumTypes.BranchStatusEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author marek_j on 2018-06-02
 */
public class BranchGenerator {

    private Random random = new Random();
    private List<AddressBranchEntity> addressList;

    public BranchGenerator(List<AddressBranchEntity> addressList) {
        this.addressList = addressList;
    }

    public List<BranchEntity> generate(Integer numberOfSamples) {
        List<BranchEntity> entities = new ArrayList<>();
        for (int i = 0; i < numberOfSamples; i++) {
            entities.add(generateRandomBranch());
        }
        return entities;
    }

    private BranchEntity generateRandomBranch() {
        Integer index = random.nextInt(addressList.size());
        AddressBranchEntity addressBranchEntity = addressList.get(index);

        return new BranchEntity().builder()
                .address(addressBranchEntity)
                .status(generateBranchStatus())
                .build();
    }

    private BranchStatusEnum generateBranchStatus() {
        List<BranchStatusEnum> statusList = Arrays.asList(BranchStatusEnum.OPEN,BranchStatusEnum.CLOSED,BranchStatusEnum.TEMPORARILY_UNAVAILABLE);
        Integer index = random.nextInt(statusList.size());
        return statusList.get(index);
    }
}
