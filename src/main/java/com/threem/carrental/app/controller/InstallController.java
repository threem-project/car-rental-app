package com.threem.carrental.app.controller;

import com.threem.carrental.app.model.dto.InstallDto;
import com.threem.carrental.app.model.dto.InstallerViewDto;
import com.threem.carrental.app.service.InstallService;
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
    public ResponseEntity<InstallerViewDto> setupDbConnectionAndProperties(@RequestBody @Validated InstallDto installDto) {
        InstallerViewDto installerViewDto = installService.createMainOffice(installDto);
        if (installerViewDto !=null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(installerViewDto);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


}
