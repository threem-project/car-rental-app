package com.threem.carrental.app.model.dto;

import com.threem.carrental.app.model.entity.BookingEntity;
import com.threem.carrental.app.model.entity.CarEntity;
import com.threem.carrental.app.model.entity.EmployeeEntity;
import com.threem.carrental.app.model.entity.MainOfficeEntity;
import com.threem.carrental.app.model.entity.enumTypes.BranchStatusEnum;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * @author misza_lemko on 22.05.2018
 * @project car-rental-app
 */

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter(AccessLevel.PRIVATE)
@Getter
public class BranchDto {

    private Long id;

    @NotNull(message = "Address can't be empty")
    private AddressBranchDto address;

    private MainOfficeEntity mainOffice;

    private List<BookingEntity> bookings;

    private List<EmployeeEntity> employees;

    private List<CarEntity> cars;

    private BranchStatusEnum branchStatus;

}
