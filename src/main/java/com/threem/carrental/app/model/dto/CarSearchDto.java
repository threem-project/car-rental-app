package com.threem.carrental.app.model.dto;

import com.threem.carrental.app.model.entity.CarEntity;
import lombok.*;

import java.math.BigDecimal;

/**
 * @author marek_j on 2018-06-22
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
public class CarSearchDto {

    private CarEntity carEntity;
    private Integer capacityFrom;
    private Integer capacityTo;
    private Integer yearFrom;
    private Integer yearTo;
    private Integer seatsFrom;
    private Integer seatsTo;
    private Integer doorsFrom;
    private Integer doorsTo;
    private BigDecimal dailyRateFrom;
    private BigDecimal dailyRateTo;
}
