package com.threem.carrental.installer.model.outbound.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * @author marek_j on 2018-05-10
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter (AccessLevel.PRIVATE)
public class InstallerView {

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "domain")
    private String domain;

    @JsonProperty(value = "address")
    private String address;

    @JsonProperty(value = "owner_first_name")
    private String firstName;

    @JsonProperty(value = "owner_last_name")
    private String lastName;

    @JsonProperty(value = "owner_email")
    private String email;

    @JsonProperty(value = "password")
    private String password;

}
