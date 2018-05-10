package com.threem.carrental.installer.controller;

import com.threem.carrental.installer.model.inbound.command.InstallCreateCommand;
import com.threem.carrental.installer.model.outbound.view.InstallerView;
import com.threem.carrental.installer.service.InstallService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author marek_j on 2018-05-08
 */
@RestController
@RequestMapping("install")
public class InstallController {

    private static final Logger LOG = LoggerFactory.getLogger(InstallController.class);
    private InstallService installService;

    public InstallController(InstallService installService) {
        this.installService = installService;
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<InstallerView> setupDbConnectionAndProperties(@RequestBody @Validated InstallCreateCommand installCreateCommand) {
        InstallerView installerView = installService.createMainOffice(installCreateCommand);
        if (installerView!=null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(installerView);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


}
