package com.threem.carrental.app.model.dto;

import com.threem.carrental.app.model.entity.enumTypes.EmployeeRoleEnum;
import com.threem.carrental.app.model.entity.enumTypes.EmployeeStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author marek_j on 2018-05-22
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class EmployeeDto {

    private Long employeeId;

    @NotBlank
    @Size(min = 3, max = 100)
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 100)
    private String lastName;

    private EmployeeRoleEnum role;
    private EmployeeStatusEnum status;

    @NotBlank
    @Size(min = 8, max = 16)
    private String password;

    @NotBlank
    @Size(min = 3, max = 300)
    private String email;

    private Long branchId;

//    private BookingsDto bookings; //todo refactor this as soon as Bookings Dto/Entities are implemented

    public void setPassword(String password) {
        this.password = password;
    }
}
