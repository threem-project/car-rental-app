package com.threem.carrental.app.service;

import com.threem.carrental.app.model.dto.EmployeeDto;
import com.threem.carrental.app.model.entity.BranchEntity;
import com.threem.carrental.app.model.entity.enumTypes.EmployeeRoleEnum;
import com.threem.carrental.app.model.entity.enumTypes.EmployeeStatusEnum;
import com.threem.carrental.app.repository.BranchRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author marek_j on 2018-05-24
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Rollback
@Transactional
@ActiveProfiles("test")
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private BranchRepository branchRepository;

    @Test
    public void shouldCreateEmployeeWhenReceiveProperEmployeeDtoWithoutBranch() throws InterruptedException {
        BranchEntity testBranch = new BranchEntity().builder().id(Long.valueOf(1)).build();
        branchRepository.save(testBranch);

        EmployeeDto employeeDto = new EmployeeDto().builder()   //given
                .employeeId(null)
                .firstName("John")
                .lastName("Kowalski")
                .password("testPassword")
                .email("email@testdomain.com")
                .status(EmployeeStatusEnum.NEW)
                .role(EmployeeRoleEnum.REGULAR_EMPLOYEE)
                .branchId(null)
                .build();

        Optional<EmployeeDto> dtoAsSaveResult = employeeService.createEmployee(employeeDto); //when

        Assertions.assertThat(dtoAsSaveResult.get())  //then
                .hasFieldOrPropertyWithValue("firstName","John")
                .hasFieldOrPropertyWithValue("lastName","Kowalski")
                .hasFieldOrPropertyWithValue("password",null)
                .hasFieldOrPropertyWithValue("email","email@testdomain.com")
                .hasFieldOrPropertyWithValue("status",EmployeeStatusEnum.NEW)
                .hasFieldOrPropertyWithValue("branchId",null)
                .hasFieldOrPropertyWithValue("role",EmployeeRoleEnum.REGULAR_EMPLOYEE);

        Assertions.assertThat(dtoAsSaveResult.get().getEmployeeId())
                .isNotNull();
    }

    @Test
    public void shouldCreateEmployeeWhenReceiveProperEmployeeDtoWithBranch() throws InterruptedException {
        BranchEntity testBranch = new BranchEntity().builder().id(Long.valueOf(1)).build();
        branchRepository.save(testBranch);

        EmployeeDto employeeDto = new EmployeeDto().builder()   //given
                .employeeId(null)
                .firstName("John")
                .lastName("Kowalski")
                .password("testPassword")
                .email("email@testdomain.com")
                .status(EmployeeStatusEnum.NEW)
                .role(EmployeeRoleEnum.REGULAR_EMPLOYEE)
                .branchId(Long.valueOf(1))
                .build();

        Optional<EmployeeDto> dtoAsSaveResult = employeeService.createEmployee(employeeDto); //when

        Assertions.assertThat(dtoAsSaveResult.get())  //then
                .hasFieldOrPropertyWithValue("firstName","John")
                .hasFieldOrPropertyWithValue("lastName","Kowalski")
                .hasFieldOrPropertyWithValue("password",null)
                .hasFieldOrPropertyWithValue("email","email@testdomain.com")
                .hasFieldOrPropertyWithValue("status",EmployeeStatusEnum.NEW)
                .hasFieldOrPropertyWithValue("branchId",Long.valueOf(1))
                .hasFieldOrPropertyWithValue("role",EmployeeRoleEnum.REGULAR_EMPLOYEE);

        Assertions.assertThat(dtoAsSaveResult.get().getEmployeeId())
                .isNotNull();
    }

}