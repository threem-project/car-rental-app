package com.threem.carrental.app.service;

import com.threem.carrental.app.errorHandler.customExceptions.EmployeeAlreadyExistException;
import com.threem.carrental.app.errorHandler.customExceptions.EmployeeDoesNotExistException;
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
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    private EmployeeEntity testEmployee;
    private EmployeeEntity testEmployeeUpdated;
    private BranchEntity testBranch;

    @Before
    public void setup() {
        testEmployee = new EmployeeEntity().builder()
                .id(null)
                .firstName("John")
                .lastName("Kowalski")
                .password("testPassword")
                .email("email@testdomain.com")
                .status(EmployeeStatusEnum.NEW)
                .role(EmployeeRoleEnum.REGULAR_EMPLOYEE)
                .branch(null)
                .build();

        testEmployeeUpdated = new EmployeeEntity().builder()
                .id(null)
                .firstName("test")
                .lastName("test")
                .password("testtest")
                .email("testTest")
                .status(EmployeeStatusEnum.ACTIVE)
                .role(EmployeeRoleEnum.OWNER)
                .branch(null)
                .build();

        testBranch = new BranchEntity().builder()
                .id(null)
                .build();
    }

    @Test
    public void shouldCreateEmployeeWhenReceiveProperEmployeeWithoutBranch() throws InterruptedException {
        //given
        //when
        Optional<EmployeeEntity> actualEntityOptional = employeeService.createEmployee(testEmployee);
        //then
        Assertions.assertThat(actualEntityOptional.get())
                .hasFieldOrPropertyWithValue("firstName","John")
                .hasFieldOrPropertyWithValue("lastName","Kowalski")
                .hasFieldOrPropertyWithValue("password",null)
                .hasFieldOrPropertyWithValue("email","email@testdomain.com")
                .hasFieldOrPropertyWithValue("status",EmployeeStatusEnum.NEW)
                .hasFieldOrPropertyWithValue("branch",null)
                .hasFieldOrPropertyWithValue("role",EmployeeRoleEnum.REGULAR_EMPLOYEE);
        Assertions.assertThat(actualEntityOptional.get().getId()).isNotNull();
    }

    @Test
    public void shouldCreateEmployeeWhenReceiveProperEmployeeDtoWithBranch() throws InterruptedException {
        //given
        branchRepository.save(testBranch);
        testEmployee.setBranch(testBranch);
        //when
        Optional<EmployeeEntity> actualEntityOptional = employeeService.createEmployee(testEmployee);
        //then
        Assertions.assertThat(actualEntityOptional.get())  //then
                .hasFieldOrPropertyWithValue("firstName","John")
                .hasFieldOrPropertyWithValue("lastName","Kowalski")
                .hasFieldOrPropertyWithValue("password",null)
                .hasFieldOrPropertyWithValue("email","email@testdomain.com")
                .hasFieldOrPropertyWithValue("status",EmployeeStatusEnum.NEW)
                .hasFieldOrPropertyWithValue("branch",testBranch)
                .hasFieldOrPropertyWithValue("role",EmployeeRoleEnum.REGULAR_EMPLOYEE);
        Assertions.assertThat(actualEntityOptional.get().getId()).isNotNull();
    }

    @Test(expected = EmployeeAlreadyExistException.class)
    public void shouldThrowEmployeeAlreadyExistExceptionWhenEmployeeExistsInDb() {
        //given
        branchRepository.save(testBranch);
        testEmployee.setBranch(testBranch);
        employeeRepository.save(testEmployee);
        EmployeeEntity actualEntity = new EmployeeEntity().builder()
                .id(testEmployee.getId())
                .build();
        //when
        employeeService.createEmployee(actualEntity);
        //then
    }

    @Test
    public void shouldUpdateExistingEmployeeWhenReceiveProperEmployeeWithoutBranch() {
        //given
        employeeRepository.save(testEmployee);
        testEmployeeUpdated.setId(testEmployee.getId());
        //when
        Optional<EmployeeEntity> entityAsUpdateResult = employeeService.updateEmployee(testEmployeeUpdated);
        //then
        Assertions.assertThat(entityAsUpdateResult.get())  //then
                .hasFieldOrPropertyWithValue("id",testEmployeeUpdated.getId())
                .hasFieldOrPropertyWithValue("firstName","test")
                .hasFieldOrPropertyWithValue("lastName","test")
                .hasFieldOrPropertyWithValue("password",null)
                .hasFieldOrPropertyWithValue("email","testTest")
                .hasFieldOrPropertyWithValue("status",EmployeeStatusEnum.ACTIVE)
                .hasFieldOrPropertyWithValue("branch",null)
                .hasFieldOrPropertyWithValue("role",EmployeeRoleEnum.OWNER);
    }

    @Test
    public void shouldUpdateExistingEmployeeWhenReceiveProperEmployeeWithBranch() {
        //given
        branchRepository.save(testBranch);
        testEmployee.setBranch(testBranch);
        employeeRepository.save(testEmployee);
        testEmployeeUpdated.setId(testEmployee.getId());
        testEmployeeUpdated.setBranch(testBranch);
        //when
        Optional<EmployeeEntity> entityAsUpdateResult = employeeService.updateEmployee(testEmployeeUpdated);
        //then
        Assertions.assertThat(entityAsUpdateResult.get())  //then
                .hasFieldOrPropertyWithValue("id",testEmployeeUpdated.getId())
                .hasFieldOrPropertyWithValue("firstName","test")
                .hasFieldOrPropertyWithValue("lastName","test")
                .hasFieldOrPropertyWithValue("password",null)
                .hasFieldOrPropertyWithValue("email","testTest")
                .hasFieldOrPropertyWithValue("status",EmployeeStatusEnum.ACTIVE)
                .hasFieldOrPropertyWithValue("branch",testBranch)
                .hasFieldOrPropertyWithValue("role",EmployeeRoleEnum.OWNER);
    }

    @Test(expected = EmployeeDoesNotExistException.class)
    public void shouldNotUpdateNotExistingEmployee() {
        //given
        EmployeeEntity employeeEntity = new EmployeeEntity().builder()
                .id(0L)
                .build();
        //when
        employeeService.updateEmployee(employeeEntity);
    }

    @Test
    public void shouldFindEmployeeWhenExistingInDb() {
        //given
        branchRepository.save(testBranch);
        testEmployee.setBranch(testBranch);
        employeeRepository.save(testEmployee);
        //when
        Optional<EmployeeEntity> entityAsSearchResult = employeeService.findById(testEmployee.getId()); //when
        //then
        Assertions.assertThat(entityAsSearchResult.get()).isNotNull();
        Assertions.assertThat(entityAsSearchResult.get())
                .hasFieldOrPropertyWithValue("id",testEmployee.getId())
                .hasFieldOrPropertyWithValue("firstName","John")
                .hasFieldOrPropertyWithValue("lastName","Kowalski")
                .hasFieldOrPropertyWithValue("password",null)
                .hasFieldOrPropertyWithValue("email","email@testdomain.com")
                .hasFieldOrPropertyWithValue("status",EmployeeStatusEnum.NEW)
                .hasFieldOrPropertyWithValue("branch",testBranch)
                .hasFieldOrPropertyWithValue("role",EmployeeRoleEnum.REGULAR_EMPLOYEE);
    }

    @Test
    public void shouldNotFindEmployeeWhenNonExistingInDb() {
        Long fakeId = 0L; //given
        Optional<EmployeeEntity> employeeOptional = employeeService.findById(fakeId); //when
        Assertions.assertThat(employeeOptional).isEqualTo(Optional.empty()); //then
    }

    @Test
    public void shouldFindAllEmployeesPaginated() {
        //given
        generateEntities(10);
        Integer sizeBeforeChange = employeeRepository.findAll().size();
        Page<EmployeeEntity> paginatedBefore = employeeService.findAllPaginated(0,sizeBeforeChange);
        generateEntities(10);
        //when
        Page<EmployeeEntity> paginatedAfter = employeeService.findAllPaginated(0,sizeBeforeChange);
        //then
        Assertions.assertThat(paginatedBefore.getTotalPages()).isLessThan(paginatedAfter.getTotalPages());
    }

    private void generateEntities(Integer number) {
        for (int i=0;i<number; i++) {
            EmployeeEntity employeeEntity = new EmployeeEntity().builder().firstName("test " + i).build();
            employeeRepository.save(employeeEntity);
        }
    }
}