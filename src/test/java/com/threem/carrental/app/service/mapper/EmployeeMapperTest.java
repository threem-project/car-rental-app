package com.threem.carrental.app.service.mapper;

import com.threem.carrental.app.model.dto.EmployeeDto;
import com.threem.carrental.app.model.entity.BranchEntity;
import com.threem.carrental.app.model.entity.EmployeeEntity;
import com.threem.carrental.app.model.entity.enumTypes.EmployeeRoleEnum;
import com.threem.carrental.app.model.entity.enumTypes.EmployeeStatusEnum;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

/**
 * @author marek_j on 2018-05-24
 */
public class EmployeeMapperTest {

    private EmployeeMapper mapper;

//    @Before
//    public void setup() {
//        mapper = new EmployeeMapper();
//    }
//
//    @Test
//    public void shouldMapEmployeeDtoToEmployeeEntityWithBranch() {
//        EmployeeDto employeeDto = new EmployeeDto().builder()   //given
//                .employeeId(Long.valueOf(1))
//                .firstName("John")
//                .lastName("Kowalski")
//                .password("testPassword")
//                .email("email@testdomain.com")
//                .status(EmployeeStatusEnum.NEW)
//                .role(EmployeeRoleEnum.REGULAR_EMPLOYEE)
//                .branchId(Long.valueOf(1))
//                .build();
//
//        EmployeeEntity employeeEntity = mapper.toEmployeeEntity(employeeDto);   //when
//
//        Long expectedBranchEntityId = employeeEntity.getBranch().getId();   //then
//        Assertions.assertThat(expectedBranchEntityId).isEqualTo(employeeDto.getBranchId());
//
//        Assertions.assertThat(employeeEntity)   //then
//                .hasFieldOrPropertyWithValue("id",employeeDto.getEmployeeId())
//                .hasFieldOrPropertyWithValue("firstName",employeeDto.getFirstName())
//                .hasFieldOrPropertyWithValue("lastName",employeeDto.getLastName())
//                .hasFieldOrPropertyWithValue("role",employeeDto.getRole())
//                .hasFieldOrPropertyWithValue("status",employeeDto.getStatus())
//                .hasFieldOrPropertyWithValue("password",employeeDto.getPassword())
//                .hasFieldOrPropertyWithValue("email",employeeDto.getEmail());
//    }
//
//    @Test
//    public void shouldMapEmployeeDtoToEmployeeEntityWithNoBranch() {
//        EmployeeDto employeeDto = new EmployeeDto().builder()   //given
//                .employeeId(Long.valueOf(1))
//                .firstName("John")
//                .lastName("Kowalski")
//                .password("testPassword")
//                .email("email@testdomain.com")
//                .status(EmployeeStatusEnum.NEW)
//                .role(EmployeeRoleEnum.REGULAR_EMPLOYEE)
//                .branchId(null)
//                .build();
//
//        EmployeeEntity employeeEntity = mapper.toEmployeeEntity(employeeDto);   //when
//
//        Assertions.assertThat(employeeEntity)   //then
//                .hasFieldOrPropertyWithValue("id",employeeDto.getEmployeeId())
//                .hasFieldOrPropertyWithValue("firstName",employeeDto.getFirstName())
//                .hasFieldOrPropertyWithValue("lastName",employeeDto.getLastName())
//                .hasFieldOrPropertyWithValue("role",employeeDto.getRole())
//                .hasFieldOrPropertyWithValue("status",employeeDto.getStatus())
//                .hasFieldOrPropertyWithValue("password",employeeDto.getPassword())
//                .hasFieldOrPropertyWithValue("branch.id",null)
//                .hasFieldOrPropertyWithValue("email",employeeDto.getEmail());
//    }
//
//    @Test
//    public void shouldMapEmployeeEntityToEmployeeDtoWithBranch() {
//        BranchEntity branchEntity = new BranchEntity().builder()
//                .id(Long.valueOf(10))
//                .build();
//
//        EmployeeEntity employeeEntity = new EmployeeEntity().builder()  //given
//                .id(Long.valueOf(1))
//                .firstName("John")
//                .lastName("Kowalski")
//                .role(EmployeeRoleEnum.REGULAR_EMPLOYEE)
//                .status(EmployeeStatusEnum.NEW)
//                .password("testPassword")
//                .branch(branchEntity)
//                .email("email@testdomain.com")
//                .build();
//
//        EmployeeDto employeeDto = mapper.toEmployeeDto(employeeEntity); //when
//
//        Assertions.assertThat(employeeDto)  //then
//                .hasFieldOrPropertyWithValue("employeeId",employeeEntity.getId())
//                .hasFieldOrPropertyWithValue("firstName",employeeEntity.getFirstName())
//                .hasFieldOrPropertyWithValue("lastName",employeeEntity.getLastName())
//                .hasFieldOrPropertyWithValue("password",employeeEntity.getPassword())
//                .hasFieldOrPropertyWithValue("email",employeeEntity.getEmail())
//                .hasFieldOrPropertyWithValue("status",employeeEntity.getStatus())
//                .hasFieldOrPropertyWithValue("branchId",employeeEntity.getBranch().getId())
//                .hasFieldOrPropertyWithValue("role",employeeEntity.getRole());
//    }
//
//    @Test
//    public void shouldMapEmployeeEntityToEmployeeDtoWithNoBranch() {
//        EmployeeEntity employeeEntity = new EmployeeEntity().builder()  //given
//                .id(Long.valueOf(1))
//                .firstName("John")
//                .lastName("Kowalski")
//                .role(EmployeeRoleEnum.REGULAR_EMPLOYEE)
//                .status(EmployeeStatusEnum.NEW)
//                .password("testPassword")
//                .branch(null)
//                .email("email@testdomain.com")
//                .build();
//
//        EmployeeDto employeeDto = mapper.toEmployeeDto(employeeEntity); //when
//
//        Assertions.assertThat(employeeDto)  //then
//                .hasFieldOrPropertyWithValue("employeeId",employeeEntity.getId())
//                .hasFieldOrPropertyWithValue("firstName",employeeEntity.getFirstName())
//                .hasFieldOrPropertyWithValue("lastName",employeeEntity.getLastName())
//                .hasFieldOrPropertyWithValue("password",employeeEntity.getPassword())
//                .hasFieldOrPropertyWithValue("email",employeeEntity.getEmail())
//                .hasFieldOrPropertyWithValue("status",employeeEntity.getStatus())
//                .hasFieldOrPropertyWithValue("branchId",employeeEntity.getBranch())
//                .hasFieldOrPropertyWithValue("role",employeeEntity.getRole());
//    }
//
//    @Test
//    public void shouldMapEmployeeEntityToEmployeeEntityWithBranch() {
//        BranchEntity branchEntity = new BranchEntity().builder()
//                .id(Long.valueOf(10))
//                .build();
//
//        EmployeeEntity employeeEntity = new EmployeeEntity().builder()  //given
//                .id(Long.valueOf(1))
//                .firstName("John")
//                .lastName("Kowalski")
//                .role(EmployeeRoleEnum.REGULAR_EMPLOYEE)
//                .status(EmployeeStatusEnum.NEW)
//                .password("testPassword")
//                .branch(branchEntity)
//                .email("email@testdomain.com")
//                .build();
//
//        EmployeeEntity mappedEntity = mapper.toEmployeeEntity(employeeEntity); //when
//
//        Long expectedBranchEntityId = employeeEntity.getBranch().getId();   //then
//        Assertions.assertThat(expectedBranchEntityId).isEqualTo(mappedEntity.getBranch().getId());
//
//        Assertions.assertThat(mappedEntity)  //then
//                .hasFieldOrPropertyWithValue("id",employeeEntity.getId())
//                .hasFieldOrPropertyWithValue("firstName",employeeEntity.getFirstName())
//                .hasFieldOrPropertyWithValue("lastName",employeeEntity.getLastName())
//                .hasFieldOrPropertyWithValue("password",employeeEntity.getPassword())
//                .hasFieldOrPropertyWithValue("email",employeeEntity.getEmail())
//                .hasFieldOrPropertyWithValue("status",employeeEntity.getStatus())
//                .hasFieldOrPropertyWithValue("role",employeeEntity.getRole());
//    }
//
//
//    @Test
//    public void shouldMapEmployeeEntityToEmployeeEntityWithNoBranch() {
//        EmployeeEntity employeeEntity = new EmployeeEntity().builder()  //given
//                .id(Long.valueOf(1))
//                .firstName("John")
//                .lastName("Kowalski")
//                .role(EmployeeRoleEnum.REGULAR_EMPLOYEE)
//                .status(EmployeeStatusEnum.NEW)
//                .password("testPassword")
//                .branch(null)
//                .email("email@testdomain.com")
//                .build();
//
//        EmployeeEntity mappedEntity = mapper.toEmployeeEntity(employeeEntity); //when
//
//        Assertions.assertThat(mappedEntity)  //then
//                .hasFieldOrPropertyWithValue("id",employeeEntity.getId())
//                .hasFieldOrPropertyWithValue("firstName",employeeEntity.getFirstName())
//                .hasFieldOrPropertyWithValue("lastName",employeeEntity.getLastName())
//                .hasFieldOrPropertyWithValue("password",employeeEntity.getPassword())
//                .hasFieldOrPropertyWithValue("email",employeeEntity.getEmail())
//                .hasFieldOrPropertyWithValue("status",employeeEntity.getStatus())
//                .hasFieldOrPropertyWithValue("branch.id",null)
//                .hasFieldOrPropertyWithValue("role",employeeEntity.getRole());
//    }
}