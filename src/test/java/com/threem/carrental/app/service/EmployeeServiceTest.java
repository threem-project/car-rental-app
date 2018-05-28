package com.threem.carrental.app.service;

import com.threem.carrental.app.errorHandler.customExceptions.EmployeeAlreadyExistException;
import com.threem.carrental.app.errorHandler.customExceptions.EmployeeDoesNotExistException;
import com.threem.carrental.app.model.dto.EmployeeDto;
import com.threem.carrental.app.model.entity.BranchEntity;
import com.threem.carrental.app.model.entity.EmployeeEntity;
import com.threem.carrental.app.model.entity.enumTypes.EmployeeRoleEnum;
import com.threem.carrental.app.model.entity.enumTypes.EmployeeStatusEnum;
import com.threem.carrental.app.repository.BranchRepository;
import com.threem.carrental.app.repository.EmployeeRepository;
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

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void shouldCreateEmployeeWhenReceiveProperEmployeeDtoWithoutBranch() throws InterruptedException {
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

    @Test(expected = EmployeeAlreadyExistException.class)
    public void shouldNotCreateEmployeeWhenEmployeeExistsInDb() {
        BranchEntity testBranch = new BranchEntity().builder().id(Long.valueOf(1)).build(); //given
        branchRepository.save(testBranch);

        EmployeeEntity testEmployee = new EmployeeEntity().builder().id(Long.valueOf(1)).build();
        EmployeeEntity savedEntity = employeeRepository.save(testEmployee);

        EmployeeDto employeeDto = new EmployeeDto().builder()
                .employeeId(savedEntity.getId())
                .firstName("John")
                .lastName("Kowalski")
                .password("testPassword")
                .email("email@testdomain.com")
                .status(EmployeeStatusEnum.NEW)
                .role(EmployeeRoleEnum.REGULAR_EMPLOYEE)
                .branchId(Long.valueOf(1))
                .build();

        employeeService.createEmployee(employeeDto);
    }

    @Test
    public void shouldUpdateExistingEmployeeWhenReceiveProperEmployeeDtoWithoutBranch() {
        BranchEntity testBranch = new BranchEntity().builder().id(Long.valueOf(1)).build(); //given
        BranchEntity savedBranchEntity = branchRepository.save(testBranch);

        EmployeeEntity employeeEntity = new EmployeeEntity().builder()
                .id(null)
                .firstName("test")
                .lastName("test")
                .email("testTest")
                .password("testtest")
                .branch(savedBranchEntity)
                .role(EmployeeRoleEnum.OWNER)
                .status(EmployeeStatusEnum.NEW)
                .build();
        EmployeeEntity savedEntity = employeeRepository.save(employeeEntity);

        EmployeeDto employeeDto = new EmployeeDto().builder()
                .employeeId(savedEntity.getId())
                .firstName("John")
                .lastName("Kowalski")
                .password("testPassword")
                .email("email@testdomain.com")
                .status(EmployeeStatusEnum.ACTIVE)
                .role(EmployeeRoleEnum.REGULAR_EMPLOYEE)
                .branchId(null)
                .build();

        Optional<EmployeeDto> dtoAsSaveResult = employeeService.updateEmployee(employeeDto); //when

        Assertions.assertThat(dtoAsSaveResult.get())  //then
                .hasFieldOrPropertyWithValue("employeeId",savedEntity.getId())
                .hasFieldOrPropertyWithValue("firstName","John")
                .hasFieldOrPropertyWithValue("lastName","Kowalski")
                .hasFieldOrPropertyWithValue("password",null)
                .hasFieldOrPropertyWithValue("email","email@testdomain.com")
                .hasFieldOrPropertyWithValue("status",EmployeeStatusEnum.ACTIVE)
                .hasFieldOrPropertyWithValue("branchId",null)
                .hasFieldOrPropertyWithValue("role",EmployeeRoleEnum.REGULAR_EMPLOYEE);
    }


    @Test
    public void shouldUpdateExistingEmployeeWhenReceiveProperEmployeeDtoWithBranch() {
        EmployeeEntity employeeEntity = new EmployeeEntity().builder() //given
                .id(null)
                .firstName("test")
                .lastName("test")
                .email("testTest")
                .password("testtest")
                .branch(null)
                .role(EmployeeRoleEnum.OWNER)
                .status(EmployeeStatusEnum.NEW)
                .build();

        EmployeeEntity savedEntity = employeeRepository.save(employeeEntity);

        EmployeeDto employeeDto = new EmployeeDto().builder()
                .employeeId(savedEntity.getId())
                .firstName("John")
                .lastName("Kowalski")
                .password("testPassword")
                .email("email@testdomain.com")
                .status(EmployeeStatusEnum.ACTIVE)
                .role(EmployeeRoleEnum.REGULAR_EMPLOYEE)
                .branchId(null)
                .build();

        Optional<EmployeeDto> dtoAsSaveResult = employeeService.updateEmployee(employeeDto); //when

        Assertions.assertThat(dtoAsSaveResult.get())  //then
                .hasFieldOrPropertyWithValue("employeeId",savedEntity.getId())
                .hasFieldOrPropertyWithValue("firstName","John")
                .hasFieldOrPropertyWithValue("lastName","Kowalski")
                .hasFieldOrPropertyWithValue("password",null)
                .hasFieldOrPropertyWithValue("email","email@testdomain.com")
                .hasFieldOrPropertyWithValue("status",EmployeeStatusEnum.ACTIVE)
                .hasFieldOrPropertyWithValue("branchId",null)
                .hasFieldOrPropertyWithValue("role",EmployeeRoleEnum.REGULAR_EMPLOYEE);
    }

    @Test(expected = EmployeeDoesNotExistException.class)
    public void shouldNotUpdateNotExistingEmployee() {
        EmployeeDto employeeDto = new EmployeeDto().builder()  //given
                .employeeId(Long.valueOf(0))
                .firstName("John")
                .lastName("Kowalski")
                .password("testPassword")
                .email("email@testdomain.com")
                .status(EmployeeStatusEnum.ACTIVE)
                .role(EmployeeRoleEnum.REGULAR_EMPLOYEE)
                .branchId(null)
                .build();

        employeeService.updateEmployee(employeeDto); //when
    }

    @Test
    public void shouldFindEmployeeWhenExistingInDb() {
        BranchEntity testBranch = new BranchEntity().builder().id(null).build(); //given
        BranchEntity savedBranchEntity = branchRepository.save(testBranch);

        EmployeeEntity employeeEntity = new EmployeeEntity().builder() //given
                .id(null)
                .firstName("John")
                .lastName("Kowalski")
                .email("email@testdomain.com")
                .password("testtest")
                .branch(savedBranchEntity)
                .role(EmployeeRoleEnum.REGULAR_EMPLOYEE)
                .status(EmployeeStatusEnum.ACTIVE)
                .build();

        EmployeeEntity savedEntity = employeeRepository.save(employeeEntity);

        Optional<EmployeeDto> employeeDtoOptional = employeeService.findById(savedEntity.getId()); //when

        Assertions.assertThat(employeeDtoOptional.get()).isNotNull();

        Assertions.assertThat(employeeDtoOptional.get())  //then
                .hasFieldOrPropertyWithValue("employeeId",savedEntity.getId())
                .hasFieldOrPropertyWithValue("firstName","John")
                .hasFieldOrPropertyWithValue("lastName","Kowalski")
                .hasFieldOrPropertyWithValue("password",null)
                .hasFieldOrPropertyWithValue("email","email@testdomain.com")
                .hasFieldOrPropertyWithValue("status",EmployeeStatusEnum.ACTIVE)
                .hasFieldOrPropertyWithValue("branchId",savedBranchEntity.getId())
                .hasFieldOrPropertyWithValue("role",EmployeeRoleEnum.REGULAR_EMPLOYEE);
    }

}