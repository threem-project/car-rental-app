package com.threem.carrental.app.repository;

import com.threem.carrental.app.model.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author misza_lemko on 03.06.2018
 * @project car-rental-app
 */
@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {
}
