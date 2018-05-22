package com.threem.carrental.app.model.outbound.entity;

import lombok.*;

import javax.persistence.*;

/**
 * @author misza_lemko on 22.05.2018
 * @project car-rental-app
 */

@Entity
@Table(name = "equipment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EquipmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

}
