package com.threem.carrental.sampleDataInitializer.dataSamples;

import com.threem.carrental.app.model.entity.AddressBranchEntity;
import com.threem.carrental.app.model.entity.BranchEntity;
import com.threem.carrental.app.model.entity.enumTypes.BranchStatusEnum;
import com.threem.carrental.app.repository.AddressBranchRepository;
import com.threem.carrental.app.repository.BranchRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author marek_j on 2018-06-02
 */
@Component
public class BranchGenerator {

    private Random random = new Random();
//    private List<AddressBranchEntity> addressList;
    private AddressBranchRepository addressBranchRepository;
    private BranchRepository branchRepository;


    public BranchGenerator(BranchRepository branchRepository, AddressBranchRepository addressBranchRepository) {
        this.addressBranchRepository = addressBranchRepository;
        this.branchRepository = branchRepository;
    }

    @Transactional
    public List<BranchEntity> generateAndSaveBranchAndAddress(Integer numberOfSamples) {
        List<BranchEntity> entities = new ArrayList<>();
        AddressBranchGenerator addressBranchGenerator = new AddressBranchGenerator();
        for (int i = 0; i < numberOfSamples; i++) {
            AddressBranchEntity addressBranchEntity = addressBranchGenerator.generate();
            BranchEntity branchEntity = generate();
            branchEntity.setAddress(addressBranchEntity);
            branchRepository.save(branchEntity);
            entities.add(branchEntity);
        }
        return entities;
    }

    private BranchEntity generate() {
        return new BranchEntity().builder()
                .status(generateBranchStatus())
                .build();
    }

    private BranchStatusEnum generateBranchStatus() {
        List<BranchStatusEnum> statusList = Arrays.asList(BranchStatusEnum.OPEN,BranchStatusEnum.CLOSED,BranchStatusEnum.TEMPORARILY_UNAVAILABLE);
        Integer index = random.nextInt(statusList.size());
        return statusList.get(index);
    }
}
