package com.threem.carrental.installer.model.inbound.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author marek_j on 2018-05-08
 */
@NoArgsConstructor
@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@ToString
public class InstallCreateCommand {

    @NotBlank
    @NotNull
    @JsonProperty(value = "name")
    private String name;

    @NotBlank
    @NotNull
    @JsonProperty(value = "domain")
    private String domain;

    @NotBlank
    @NotNull
    @JsonProperty(value = "address")
    private String address;

    @NotBlank
    @NotNull
    @JsonProperty(value = "owner_first_name")
    private String firstName;

    @NotBlank
    @NotNull
    @JsonProperty(value = "owner_last_name")
    private String lastName;

    @NotBlank
    @NotNull
    @JsonProperty(value = "owner_email")
    private String email;

    @NotBlank
    @NotNull
    @JsonProperty(value = "password")
    private String password;
}
