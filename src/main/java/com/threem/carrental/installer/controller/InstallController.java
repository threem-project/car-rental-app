package com.threem.carrental.installer.controller;

import com.threem.carrental.installer.model.inbound.command.DatabaseCreateCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @PostMapping(value = "db", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<DatabaseCreateCommand> setupDbConnectionAndProperties(@RequestBody DatabaseCreateCommand dbCreateCommand) {
        LOG.info(dbCreateCommand.toString());
        return ResponseEntity.status(HttpStatus.OK).body(dbCreateCommand);
    }


}
