package com.threem.carrental.app.model.dto;

import lombok.*;

import javax.validation.constraints.NotNull;


/**
 * @author misza_lemko on 22.05.2018
 * @project car-rental-app
 */

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter(AccessLevel.PRIVATE)
@Getter
public class BranchDto {

    @NotNull(message = "Address can't be empty")
    private AddressBranchDto address;

}
