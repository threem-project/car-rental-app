package com.threem.carrental.installer.controller;

import com.threem.carrental.installer.model.inbound.command.InstallCreateCommand;
import com.threem.carrental.installer.service.InstallService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<InstallCreateCommand> setupDbConnectionAndProperties(@RequestBody @Validated InstallCreateCommand installCreateCommand) {
        //sprawdzanie mainOfficeCommmand
        //serwise
        //      -> laczy sie do db i sprawdza czy nie ma juz danych w encji main_office
        //      -> laczy sie do db i sprawdza czy InstallerEntity jest wypelnione
        //      -> tworzy encje main office oraz encje employee dla danych wlasiciela
        //      -> zapisuje w/w encje

        LOG.info(installCreateCommand.toString());
        return ResponseEntity.status(HttpStatus.OK).body(installCreateCommand);
    }


}
