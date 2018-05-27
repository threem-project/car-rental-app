package com.threem.carrental.app.model.dto;

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
public class InstallDto {

    @NotBlank
    @NotNull
    @JsonProperty(value = "company_name")
    private String companyName;

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

    @NotBlank
    @NotNull
    @JsonProperty(value = "phone")
    private String phone;
}
