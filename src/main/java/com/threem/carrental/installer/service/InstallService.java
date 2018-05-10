package com.threem.carrental.installer.service;

import com.threem.carrental.installer.model.inbound.command.InstallCreateCommand;
import com.threem.carrental.installer.model.outbound.view.InstallerView;
import com.threem.carrental.installer.repository.InstallRepository;
import org.springframework.stereotype.Service;

/**
 * @author marek_j on 2018-05-08
 */

@Service
public class InstallService {

    private InstallRepository installRepository;

    public InstallService(InstallRepository installRepository) {
        this.installRepository = installRepository;
    }

    public InstallerView createMainOffice(InstallCreateCommand installCreateCommand) {


        return null;
    }
}