package com.threem.carrental.app.service.mapper;

import com.threem.carrental.app.model.dto.EmployeeDto;
import com.threem.carrental.app.model.entity.EmployeeEntity;
import com.threem.carrental.app.model.entity.enumTypes.EmployeeRoleEnum;
import com.threem.carrental.app.model.entity.enumTypes.EmployeeStatusEnum;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author marek_j on 2018-05-24
 */
public class EmployeeMapperTest {

    private EmployeeMapper mapper;

    @Before
    public void setup() {
        mapper = new EmployeeMapper();
    }

    @Test
    public void shouldMapEmployeeDtoToEmployeeEntity() {
        EmployeeDto employeeDto = new EmployeeDto().builder()   //given
                .employeeId(Long.valueOf(1))
                .firstName("John")
                .lastName("Kowalski")
                .password("testPassword")
                .email("email@testdomain.com")
                .status(EmployeeStatusEnum.NEW)
                .role(EmployeeRoleEnum.REGULAR_EMPLOYEE)
                .branchId(Long.valueOf(1))
                .build();

        EmployeeEntity employeeEntity = mapper.ToEmployeeEntity(employeeDto);   //when

        Assertions.assertThat(employeeEntity)   //then
                .hasFieldOrPropertyWithValue("id",employeeDto.getEmployeeId())
                .hasFieldOrPropertyWithValue("firstName",employeeDto.getFirstName())
                .hasFieldOrPropertyWithValue("lastName",employeeDto.getLastName())
                .hasFieldOrPropertyWithValue("role",employeeDto.getRole())
                .hasFieldOrPropertyWithValue("status",employeeDto.getStatus())
                .hasFieldOrPropertyWithValue("password",employeeDto.getPassword())
//                .hasFieldOrPropertyWithValue("branch",employeeDto.getBranchId()) //todo refactor this as soon, as there is branchEntity implementation
                .hasFieldOrPropertyWithValue("email",employeeDto.getEmail())
                .hasFieldOrPropertyWithValue("bookings",null);
    }

    @Test
    public void shouldMapEmployeeEntityToEmployeeDto() {
        EmployeeEntity employeeEntity = new EmployeeEntity().builder()  //given
                .id(Long.valueOf(1))
                .firstName("John")
                .lastName("Kowalski")
                .role(EmployeeRoleEnum.REGULAR_EMPLOYEE)
                .status(EmployeeStatusEnum.NEW)
                .password("testPassword")
//                .branch(null) //todo refactor this as soon, as there is branchEntity implementation
                .email("email@testdomain.com")
                .bookings(null)
                .build();

        EmployeeDto employeeDto = mapper.ToEmployeeDto(employeeEntity); //when

        Assertions.assertThat(employeeDto)  //then
                .hasFieldOrPropertyWithValue("employeeId",employeeEntity.getId())
                .hasFieldOrPropertyWithValue("firstName",employeeEntity.getFirstName())
                .hasFieldOrPropertyWithValue("lastName",employeeEntity.getLastName())
                .hasFieldOrPropertyWithValue("password",employeeEntity.getPassword())
                .hasFieldOrPropertyWithValue("email",employeeEntity.getEmail())
                .hasFieldOrPropertyWithValue("status",employeeEntity.getStatus())
//                .hasFieldOrPropertyWithValue("branchId",null) //todo refactor this as soon, as there is branchEntity implementation
                .hasFieldOrPropertyWithValue("role",employeeEntity.getRole());

    }
}