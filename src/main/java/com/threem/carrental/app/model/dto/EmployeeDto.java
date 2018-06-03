package com.threem.carrental.app.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @NotBlank(message = "fistname can't be blank")
    @Size(min = 3, max = 100, message = "firstName should have from 3 to 100 characters")
    private String firstName;

    @NotBlank(message = "lastname can't be blank")
    @Size(min = 3, max = 100, message = "lastName should have from 3 to 100 characters")
    private String lastName;

    private EmployeeRoleEnum role;
    private EmployeeStatusEnum status;

    @NotBlank(message = "password can't be blank")
    @Size(min = 8, max = 16, message = "lastName should have from 3 to 16 characters")
    private String password;

    @NotBlank(message = "email can't be blank")
    @Size(min = 10, max = 300, message = "email should have from 10 to 300 characters")
    private String email;

    private Long branchId;

    public void setPassword(String password) {
        this.password = password;
    }
}
