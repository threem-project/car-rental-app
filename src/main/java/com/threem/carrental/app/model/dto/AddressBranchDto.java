package com.threem.carrental.app.model.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
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

    private Long id;

    @NotBlank(message = "The city can't be blank or empty")
    @Size(min = 3,max = 200,message = "The size of city name must be larger then 3 and smaller then 200")
    private String city;

    @NotBlank(message = "The street can't be blank or empty")
    @Size(min = 3,max = 200,message = "The size of street name must be larger then 3 and smaller then 200")
    private String street;

    @NotBlank(message = "The building can't be blank or empty")
    @Size(min = 3,max = 200,message = "The size of building number must be larger then 3 and smaller then 200")
    private String building;

    @NotBlank(message = "The zip-code can't be blank or empty")
    @Size(min = 5,max = 10,message = "The size of zip-code name must be larger then 5 and smaller then 10")
    private String zipCode;

    @NotBlank(message = "The country can't be blank or empty")
    @Size(min = 3,max = 45,message = "The size of country name must be larger then 3 and smaller then 45")
    private String country;

    @NotBlank(message = "The phone can't be blank or empty")
    @Size(min = 5,max = 20,message = "The size of phone number must be larger then 5 and smaller then 45")
    private String phone;

}
