package com.threem.carrental.installer.model.inbound.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * @author marek_j on 2018-05-08
 */
@NoArgsConstructor
@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@ToString
public class DatabaseCreateCommand {

    @JsonProperty(value = "dbUrl")
    private String dbUrl;

    @JsonProperty(value = "dbUser")
    private String dbUser;

    @JsonProperty(value = "dbPassword")
    private String dbPassword;

}
