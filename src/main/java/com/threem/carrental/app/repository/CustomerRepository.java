package com.threem.carrental.app.repository;

import com.threem.carrental.app.model.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author marek_j on 2018-06-29
 */
@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
