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

    @Test
    public void shouldCreateEmployeeWhenReceiveProperEmployeeWithoutBranch() throws InterruptedException {
        //given
        EmployeeEntity expectedEmployeeEntity = new EmployeeEntity().builder()
                .id(null)
                .firstName("John")
                .lastName("Kowalski")
                .password("testPassword")
                .email("email@testdomain.com")
                .status(EmployeeStatusEnum.NEW)
                .role(EmployeeRoleEnum.REGULAR_EMPLOYEE)
                .branch(null)
                .build();

        //when
        Optional<EmployeeEntity> actualEntityOptional = employeeService.createEmployee(expectedEmployeeEntity);
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
        BranchEntity testBranch = new BranchEntity().builder().id(null).build();
        branchRepository.save(testBranch);
        EmployeeEntity expectedEmployeeEntity = new EmployeeEntity().builder()
                .id(null)
                .firstName("John")
                .lastName("Kowalski")
                .password("testPassword")
                .email("email@testdomain.com")
                .status(EmployeeStatusEnum.NEW)
                .role(EmployeeRoleEnum.REGULAR_EMPLOYEE)
                .branch(testBranch)
                .build();

        //when
        Optional<EmployeeEntity> actualEntityOptional = employeeService.createEmployee(expectedEmployeeEntity);

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
    public void shouldNotCreateEmployeeWhenEmployeeExistsInDb() {
        //given
        BranchEntity testBranch = new BranchEntity().builder().id(null).build(); //given
        BranchEntity savedBranchEntity = branchRepository.save(testBranch);

        EmployeeEntity testEmployee = new EmployeeEntity().builder().id(null).build();
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(testEmployee);

        EmployeeEntity actualEntity = new EmployeeEntity().builder()
                .id(savedEmployeeEntity.getId())
                .firstName("John")
                .lastName("Kowalski")
                .password("testPassword")
                .email("email@testdomain.com")
                .status(EmployeeStatusEnum.NEW)
                .role(EmployeeRoleEnum.REGULAR_EMPLOYEE)
                .branch(savedBranchEntity)
                .build();
        //when
        employeeService.createEmployee(actualEntity);
    }

    @Test
    public void shouldUpdateExistingEmployeeWhenReceiveProperEmployeeWithoutBranch() {
        //given
        BranchEntity testBranch = new BranchEntity().builder().id(null).build();
        branchRepository.save(testBranch);

        EmployeeEntity employeeEntity = new EmployeeEntity().builder()
                .id(null)
                .firstName("test")
                .lastName("test")
                .email("testTest")
                .password("testtest")
                .branch(testBranch)
                .role(EmployeeRoleEnum.OWNER)
                .status(EmployeeStatusEnum.NEW)
                .build();

        employeeRepository.save(employeeEntity);

        EmployeeEntity employeeEntityForUpdate = new EmployeeEntity().builder()
                .id(employeeEntity.getId())
                .firstName("John")
                .lastName("Kowalski")
                .password("testPassword")
                .email("email@testdomain.com")
                .branch(null)
                .status(EmployeeStatusEnum.ACTIVE)
                .role(EmployeeRoleEnum.REGULAR_EMPLOYEE)
                .build();

        Optional<EmployeeEntity> entityAsUpdateResult = employeeService.updateEmployee(employeeEntityForUpdate); //when

        Assertions.assertThat(entityAsUpdateResult.get())  //then
                .hasFieldOrPropertyWithValue("id",employeeEntityForUpdate.getId())
                .hasFieldOrPropertyWithValue("firstName","John")
                .hasFieldOrPropertyWithValue("lastName","Kowalski")
                .hasFieldOrPropertyWithValue("password",null)
                .hasFieldOrPropertyWithValue("email","email@testdomain.com")
                .hasFieldOrPropertyWithValue("status",EmployeeStatusEnum.ACTIVE)
                .hasFieldOrPropertyWithValue("branch",null)
                .hasFieldOrPropertyWithValue("role",EmployeeRoleEnum.REGULAR_EMPLOYEE);
    }


    @Test
    public void shouldUpdateExistingEmployeeWhenReceiveProperEmployeeWithBranch() {
        //given
        EmployeeEntity employeeEntity = new EmployeeEntity().builder()
                .id(null)
                .firstName("test")
                .lastName("test")
                .email("testTest")
                .password("testtest")
                .branch(null)
                .role(EmployeeRoleEnum.OWNER)
                .status(EmployeeStatusEnum.NEW)
                .build();

        employeeRepository.save(employeeEntity);

        EmployeeEntity employeeEntityForUpdate = new EmployeeEntity().builder()
                .id(employeeEntity.getId())
                .firstName("John")
                .lastName("Kowalski")
                .password("testPassword")
                .email("email@testdomain.com")
                .status(EmployeeStatusEnum.ACTIVE)
                .role(EmployeeRoleEnum.REGULAR_EMPLOYEE)
                .branch(null)
                .build();

        //when
        Optional<EmployeeEntity> entityAsSaveResult = employeeService.updateEmployee(employeeEntityForUpdate);

        //then
        Assertions.assertThat(entityAsSaveResult.get())
                .hasFieldOrPropertyWithValue("id",employeeEntity.getId())
                .hasFieldOrPropertyWithValue("firstName","John")
                .hasFieldOrPropertyWithValue("lastName","Kowalski")
                .hasFieldOrPropertyWithValue("password",null)
                .hasFieldOrPropertyWithValue("email","email@testdomain.com")
                .hasFieldOrPropertyWithValue("status",EmployeeStatusEnum.ACTIVE)
                .hasFieldOrPropertyWithValue("branch",null)
                .hasFieldOrPropertyWithValue("role",EmployeeRoleEnum.REGULAR_EMPLOYEE);
    }

    @Test(expected = EmployeeDoesNotExistException.class)
    public void shouldNotUpdateNotExistingEmployee() {
        //given
        EmployeeEntity employeeEntity = new EmployeeEntity().builder()
                .id(0L)
                .firstName("John")
                .lastName("Kowalski")
                .password("testPassword")
                .email("email@testdomain.com")
                .status(EmployeeStatusEnum.ACTIVE)
                .role(EmployeeRoleEnum.REGULAR_EMPLOYEE)
                .branch(null)
                .build();
        //when
        employeeService.updateEmployee(employeeEntity);
    }

    @Test
    public void shouldFindEmployeeWhenExistingInDb() {
        //given
        BranchEntity testBranch = new BranchEntity().builder().id(null).build();
        branchRepository.save(testBranch);

        EmployeeEntity employeeEntity = new EmployeeEntity().builder()
                .id(null)
                .firstName("John")
                .lastName("Kowalski")
                .email("email@testdomain.com")
                .password("testtest")
                .branch(testBranch)
                .role(EmployeeRoleEnum.REGULAR_EMPLOYEE)
                .status(EmployeeStatusEnum.ACTIVE)
                .build();

        employeeRepository.save(employeeEntity);

        //when
        Optional<EmployeeEntity> entityAsSearchResult = employeeService.findById(employeeEntity.getId()); //when

        //then
        Assertions.assertThat(entityAsSearchResult.get()).isNotNull();
        Assertions.assertThat(entityAsSearchResult.get())
                .hasFieldOrPropertyWithValue("id",employeeEntity.getId())
                .hasFieldOrPropertyWithValue("firstName","John")
                .hasFieldOrPropertyWithValue("lastName","Kowalski")
                .hasFieldOrPropertyWithValue("password",null)
                .hasFieldOrPropertyWithValue("email","email@testdomain.com")
                .hasFieldOrPropertyWithValue("status",EmployeeStatusEnum.ACTIVE)
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
        Integer testEmployeesNumber = 10;
        for (Integer i=0;i<testEmployeesNumber;i++) {
            EmployeeEntity employeeEntity = new EmployeeEntity().builder().firstName("test " + i).build();
            employeeRepository.save(employeeEntity);
        }
        List<EmployeeEntity> listBeforeChange = employeeRepository.findAll();
        Integer sizeBeforeChange = listBeforeChange.size();
        Page<EmployeeEntity> paginatedBefore = employeeService.findAllPaginated(0,sizeBeforeChange);

        //when
        for (Integer i=0;i<testEmployeesNumber;i++) {
            EmployeeEntity employeeEntity = new EmployeeEntity().builder().firstName("test " + i).build();
            employeeRepository.save(employeeEntity);
        }
        Page<EmployeeEntity> paginatedAfter = employeeService.findAllPaginated(0,sizeBeforeChange);

        //then
        Assertions.assertThat(paginatedBefore.getTotalPages()).isLessThan(paginatedAfter.getTotalPages());
    }
}