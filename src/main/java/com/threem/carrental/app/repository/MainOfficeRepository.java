package com.threem.carrental.app.repository;

import com.threem.carrental.app.model.entity.MainOfficeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author marek_j on 2018-05-10
 */
@Repository
public interface MainOfficeRepository extends JpaRepository<MainOfficeEntity,Long>{
}
