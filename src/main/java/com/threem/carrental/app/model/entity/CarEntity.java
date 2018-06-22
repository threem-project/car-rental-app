package com.threem.carrental.app.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.threem.carrental.app.model.entity.enumTypes.*;
import lombok.*;

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
@Builder
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "vin", unique = true)
    private String vin;

    @Column(name = "make")
    private String make;

    @Column(name = "model")
    private String model;

    @Column(name = "body_type")
    @Enumerated(EnumType.STRING)
    private CarBodyTypeEnum bodyType;

    @Column(name = "year")
    private Integer year;

    @Column(name = "colour")
    @Enumerated(EnumType.STRING)
    private CarColourEnum colour;

    @Column(name = "mileage")
    private Integer mileage;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private CarStatusEnum status;

    @Column(name = "daily_rate")
    private BigDecimal dailyRate;

    @Column(name = "engine_type")
    @Enumerated(EnumType.STRING)
    private CarEngineTypeEnum engineType;

    @Column(name = "engine_capacity")
    private Integer engineCapacity;

    @Column(name = "segment")
    @Enumerated(EnumType.STRING)
    private CarSegmentTypeEnum segment;

    @Column(name = "transmission")
    @Enumerated(EnumType.STRING)
    private CarTransmissionTypeEnum transmission;

    @Column(name = "seats")
    private Integer seats;

    @Column(name = "doors")
    private Integer doors;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    private BranchEntity branch;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ElementCollection(targetClass=CarEquipmentEnum.class)
    @CollectionTable(name = "car_equipment")
    @Column(name = "description")
    @Enumerated(EnumType.STRING)
    private List<CarEquipmentEnum> equipment;
}
