package com.threem.carrental.app.model.outbound.entity;

import com.threem.carrental.app.model.outbound.entity.enumTypes.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author misza_lemko on 11.05.2018
 * @project car-rental-app
 */


@Entity
@Table(name = "car")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "label")
    private CarLabelEnum label;

    @Column(name = "model")
    private CarModelEnum model;

    @Column(name = "body_type")
    private CarBodyTypeEnum bodyType;

    @Column(name = "year")
    private String year;

    @Column(name = "colour")
    private CarColourEnum colour;

    @Column(name = "mileage")
    private Integer mileage;

    @Column(name = "status")
    private CarStatusEnum status;

    @Column(name = "daily_rade")
    private BigDecimal dailyRade;

    @Column(name = "engine_type")
    private CarEngineTypeEnum engineType;

    @Column(name = "engine_capacity")
    private Integer engineCapacity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    private BranchEntity branch;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "car_id")
    private List<BookingEntity> bookings;

    public CarEntity(CarLabelEnum label, CarModelEnum model, CarBodyTypeEnum bodyType, String year,
                     CarColourEnum colour, Integer mileage, CarStatusEnum status, BigDecimal dailyRade,
                     CarEngineTypeEnum engineType, Integer engineCapacity)
    {
        this.label = label;
        this.model = model;
        this.bodyType = bodyType;
        this.year = year;
        this.colour = colour;
        this.mileage = mileage;
        this.status = status;
        this.dailyRade = dailyRade;
        this.engineType = engineType;
        this.engineCapacity = engineCapacity;
    }
}
