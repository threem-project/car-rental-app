package com.threem.carrental.app.repository;

import com.threem.carrental.app.model.entity.CarEntity;
import com.threem.carrental.app.model.entity.enumTypes.CarTransmissionTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Marika Grzebieniowska on 27.05.2018
 * @project car-rental-app
 */

@Repository
public interface CarRepository extends JpaRepository<CarEntity,Long> {

    CarEntity findByVin(String vin);

    CarEntity findByIdAndVin(Long id, String vin);

}
