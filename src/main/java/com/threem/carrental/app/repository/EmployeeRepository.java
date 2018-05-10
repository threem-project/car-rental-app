package com.threem.carrental.app.repository;

import com.threem.carrental.app.model.outbound.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author marek_j on 2018-05-10
 */
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {
}
