package com.threem.carrental.app.model.outbound.entity;

import lombok.*;

import javax.persistence.*;

/**
 * @author marek_j on 2018-05-10
 */
@Entity
@Table(name = "main_office")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MainOfficeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "domain")
    private String domain;

    @Column(name = "address")
    private String address;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;

    @Column(name = "phone")
    private String phone;

}
