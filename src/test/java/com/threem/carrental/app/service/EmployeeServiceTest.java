package com.threem.carrental.app.service;

import com.threem.carrental.app.errorHandler.customExceptions.EmployeeAlreadyExistException;
import com.threem.carrental.app.errorHandler.customExceptions.EmployeeDoesNotExistException;
import com.threem.carrental.app.model.entity.BranchEntity;
import com.threem.carrental.app.model.entity.EmployeeEntity;
import com.threem.carrental.app.model.entity.enumTypes.EmployeeRoleEnum;
import com.threem.carrental.app.model.entity.enumTypes.EmployeeStatusEnum;
import com.threem.carrental.app.repository.BranchRepository;
import com.threem.carrental.app.repository.EmployeeRepository;
import com.threem.carrental.testEntitiesFactory.TestBranchEntityFactory;
import com.threem.carrental.testEntitiesFactory.TestEmployeeEntityFactory;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author marek_j on 2018-05-24
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Rollback
@ActiveProfiles("test")
@Transactional
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    private EmployeeEntity johnKowalskiNewRegularEmployee;
    private EmployeeEntity jebediaszJonesActiveOwner;
    private BranchEntity testBranch;

    @Before
    public void setup() {
        johnKowalskiNewRegularEmployee = TestEmployeeEntityFactory.getEntity("JOHN_KOWALSKI_NEW_REGULAR_EMPLOYEE");
        jebediaszJonesActiveOwner = TestEmployeeEntityFactory.getEntity("JEBEDIASZ_JONES_ACTIVE_OWNER");
        testBranch = TestBranchEntityFactory.getEntity("OPEN_BRANCH");
    }

    @Test
    public void shouldCreateEmployeeWhenReceiveProperEmployeeWithoutBranch() {
        //given
        //when
        Optional<EmployeeEntity> actualEntityOptional = employeeService.createEmployee(johnKowalskiNewRegularEmployee);
        //then
        Assertions.assertThat(actualEntityOptional.get())
                .hasFieldOrPropertyWithValue("firstName", "John")
                .hasFieldOrPropertyWithValue("lastName", "Kowalski")
                .hasFieldOrPropertyWithValue("password", null)
                .hasFieldOrPropertyWithValue("email", "john@kowalski")
                .hasFieldOrPropertyWithValue("status", EmployeeStatusEnum.NEW)
                .hasFieldOrPropertyWithValue("branch", null)
                .hasFieldOrPropertyWithValue("role", EmployeeRoleEnum.REGULAR_EMPLOYEE);
        Assertions.assertThat(actualEntityOptional.get().getId()).isNotNull();
    }

    @Test
    public void shouldCreateEmployeeWhenReceiveProperEmployeeWithBranch() {
        //given
        branchRepository.save(testBranch);
        johnKowalskiNewRegularEmployee.setBranch(testBranch);
        //when
        Optional<EmployeeEntity> actualEntityOptional = employeeService.createEmployee(johnKowalskiNewRegularEmployee);
        //then
        Assertions.assertThat(actualEntityOptional.get())  //then
                .hasFieldOrPropertyWithValue("firstName", "John")
                .hasFieldOrPropertyWithValue("lastName", "Kowalski")
                .hasFieldOrPropertyWithValue("password", null)
                .hasFieldOrPropertyWithValue("email", "john@kowalski")
                .hasFieldOrPropertyWithValue("status", EmployeeStatusEnum.NEW)
                .hasFieldOrPropertyWithValue("branch", testBranch)
                .hasFieldOrPropertyWithValue("role", EmployeeRoleEnum.REGULAR_EMPLOYEE);
        Assertions.assertThat(actualEntityOptional.get().getId()).isNotNull();
    }

    @Test(expected = EmployeeAlreadyExistException.class)
    public void shouldThrowEmployeeAlreadyExistExceptionWhenEmployeeExistsInDb() {
        //given
        branchRepository.save(testBranch);
        johnKowalskiNewRegularEmployee.setBranch(testBranch);
        employeeRepository.save(johnKowalskiNewRegularEmployee);
        EmployeeEntity actualEntity = new EmployeeEntity().builder()
                .id(johnKowalskiNewRegularEmployee.getId())
                .build();
        //when
        employeeService.createEmployee(actualEntity);
        //then
    }

    @Test
    public void shouldUpdateExistingEmployeeWhenReceiveProperEmployeeWithoutBranch() {
        //given
        employeeRepository.save(johnKowalskiNewRegularEmployee);
        jebediaszJonesActiveOwner.setId(johnKowalskiNewRegularEmployee.getId());
        //when
        Optional<EmployeeEntity> entityAsUpdateResult = employeeService.updateEmployee(jebediaszJonesActiveOwner);
        //then
        Assertions.assertThat(entityAsUpdateResult.get())  //then
                .hasFieldOrPropertyWithValue("id", jebediaszJonesActiveOwner.getId())
                .hasFieldOrPropertyWithValue("firstName", "Jebediasz")
                .hasFieldOrPropertyWithValue("lastName", "Jones")
                .hasFieldOrPropertyWithValue("password", null)
                .hasFieldOrPropertyWithValue("email", "jebediasz@jones")
                .hasFieldOrPropertyWithValue("status", EmployeeStatusEnum.ACTIVE)
                .hasFieldOrPropertyWithValue("branch", null)
                .hasFieldOrPropertyWithValue("role", EmployeeRoleEnum.OWNER);
    }

    @Test
    public void shouldUpdateExistingEmployeeWhenReceiveProperEmployeeWithBranch() {
        //given
        branchRepository.save(testBranch);
        johnKowalskiNewRegularEmployee.setBranch(testBranch);
        employeeRepository.save(johnKowalskiNewRegularEmployee);
        jebediaszJonesActiveOwner.setId(johnKowalskiNewRegularEmployee.getId());
        jebediaszJonesActiveOwner.setBranch(testBranch);
        //when
        Optional<EmployeeEntity> entityAsUpdateResult = employeeService.updateEmployee(jebediaszJonesActiveOwner);
        //then
        Assertions.assertThat(entityAsUpdateResult.get())  //then
                .hasFieldOrPropertyWithValue("id", jebediaszJonesActiveOwner.getId())
                .hasFieldOrPropertyWithValue("firstName", "Jebediasz")
                .hasFieldOrPropertyWithValue("lastName", "Jones")
                .hasFieldOrPropertyWithValue("password", null)
                .hasFieldOrPropertyWithValue("email", "jebediasz@jones")
                .hasFieldOrPropertyWithValue("status", EmployeeStatusEnum.ACTIVE)
                .hasFieldOrPropertyWithValue("branch", testBranch)
                .hasFieldOrPropertyWithValue("role", EmployeeRoleEnum.OWNER);
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
        johnKowalskiNewRegularEmployee.setBranch(testBranch);
        employeeRepository.save(johnKowalskiNewRegularEmployee);
        //when
        Optional<EmployeeEntity> entityAsSearchResult = employeeService.findById(johnKowalskiNewRegularEmployee.getId()); //when
        //then
        Assertions.assertThat(entityAsSearchResult.get()).isNotNull();
        Assertions.assertThat(entityAsSearchResult.get())
                .hasFieldOrPropertyWithValue("id", johnKowalskiNewRegularEmployee.getId())
                .hasFieldOrPropertyWithValue("firstName", "John")
                .hasFieldOrPropertyWithValue("lastName", "Kowalski")
                .hasFieldOrPropertyWithValue("password", null)
                .hasFieldOrPropertyWithValue("email", "john@kowalski")
                .hasFieldOrPropertyWithValue("status", EmployeeStatusEnum.NEW)
                .hasFieldOrPropertyWithValue("branch", testBranch)
                .hasFieldOrPropertyWithValue("role", EmployeeRoleEnum.REGULAR_EMPLOYEE);
    }

    @Test
    public void shouldNotFindEmployeeWhenNonExistingInDb() {
        //given
        Long fakeId = 0L;
        //when
        Optional<EmployeeEntity> employeeOptional = employeeService.findById(fakeId);
        //then
        Assertions.assertThat(employeeOptional).isEqualTo(Optional.empty());
    }

    @Test
    public void shouldFindAllEmployeesPaginated() {
        //given
        List<EmployeeEntity> entitiesCollection = createDummyEntitiesWithFirstNameTest(10);
        employeeRepository.saveAll(entitiesCollection);
        Integer numberOfEntitiesBefore = employeeRepository.findAll().size();
        Page<EmployeeEntity> paginatedBefore = employeeService.findAllPaginated(0, numberOfEntitiesBefore);

        //when
        List<EmployeeEntity> entitiesToMakeMorePagesInResult = createDummyEntitiesWithFirstNameTest(10);
        employeeRepository.saveAll(entitiesToMakeMorePagesInResult);
        Page<EmployeeEntity> paginatedAfter = employeeService.findAllPaginated(0, numberOfEntitiesBefore);

        //then
        Assertions.assertThat(paginatedBefore.getTotalPages()).isLessThan(paginatedAfter.getTotalPages());
    }

    private List<EmployeeEntity> createDummyEntitiesWithFirstNameTest(Integer numberOfEntities) {
        List<EmployeeEntity> resultList = new ArrayList<>();
        for (int i = 0; i < numberOfEntities; i++) {
            EmployeeEntity employeeEntity = TestEmployeeEntityFactory.getEntity("WITH_ALL_FIELDS_NULL");
            employeeEntity.setFirstName("test " + i);
            resultList.add(employeeEntity);
        }
        return resultList;
    }

}