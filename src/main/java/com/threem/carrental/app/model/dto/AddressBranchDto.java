package com.threem.carrental.app.model.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author misza_lemko on 22.05.2018
 * @project car-rental-app
 */

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter(AccessLevel.PRIVATE)
@Getter
public class AddressBranchDto {

    @NotBlank
    @Size(min = 3,max = 200)
    private String city;

    @NotBlank
    @Size(min = 3,max = 200)
    private String street;

    @NotBlank
    @Size(min = 3,max = 200)
    private String building;

    @NotBlank
    @Size(min = 5,max = 10)
    private String zipCode;

    @NotBlank
    @Size(min = 3,max = 45)
    private String country;

    @NotBlank
    @Size(min = 5,max = 20)
    private String phone;

}
