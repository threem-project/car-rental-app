package com.threem.carrental.app.model.entity;

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

    @Column(name = "label")
    @Enumerated(EnumType.STRING)
    private CarLabelEnum label;

    @Column(name = "model")
    @Enumerated(EnumType.STRING)
    private CarModelEnum model;

    @Column(name = "body_type")
    @Enumerated(EnumType.STRING)
    private CarBodyTypeEnum bodyType;

    @Column(name = "year")
    private String year;

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
    private CarSegmentTypeEnum segment;

    @Column(name = "transmission")
    @Enumerated(EnumType.STRING)
    private CarTransmissionTypeEnum transmission;

    @Column(name = "seats")
    private Integer seats;

    @Column(name = "doors")
    private Integer doors;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    private BranchEntity branch;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "car_equipment",
            joinColumns ={@JoinColumn(name = "car_id")},
            inverseJoinColumns = {@JoinColumn(name = "equipment_id")}
    )
    private List<EquipmentEntity> equipment;

}
