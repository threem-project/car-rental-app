package com.threem.carrental.app.repository;

import com.threem.carrental.app.model.entity.BranchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author misza_lemko on 22.05.2018
 * @project car-rental-app
 */

@Repository
public interface BranchRepository extends JpaRepository<BranchEntity,Long> {
}
