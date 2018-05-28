package com.threem.carrental.app.service.mapper;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Marika Grzebieniowska on 28.05.2018
 * @project car-rental-app
 */
public class CarMapperTest {

    private CarMapper carMapper;

    @Before
    public void setUp() {
        carMapper = new CarMapper();
    }

    // Dto can have BranchId (if update) and have none (creating new car)

    @Test
    public void shouldMapCarDtoToCarEntityWithBranchId() {

        //given

        //when

        //then
    }

    @Test
    public void shouldMapCarDtoToCarEntityWithNoBranchId() {

        //given

        //when

        //then
    }

    @Test
    public void shouldMapCarEntityToCarDtoBranch() {

        //given

        //when

        //then

    }

    @Test
    public void shouldMapCarEntityToCarDtoWithNoBranch(){

        //given

        //when

        //then

    }

}