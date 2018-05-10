package com.threem.carrental.installer.repository;

import com.threem.carrental.installer.model.outbound.entity.InstallerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author marek_j on 2018-05-10
 */
public interface InstallRepository extends JpaRepository<InstallerEntity, Integer> {
}
