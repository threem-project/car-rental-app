package com.threem.carrental.app.repository;

import com.threem.carrental.app.model.entity.EquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Marika Grzebieniowska on 02.06.2018
 * @project car-rental-app
 */
@Repository
public interface EquipmentRepository extends JpaRepository<EquipmentEntity,Long> {

}
