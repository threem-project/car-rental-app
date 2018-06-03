package com.threem.carrental.app.repository;

import com.threem.carrental.app.model.entity.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author marek_j on 2018-05-10
 */
@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {}
