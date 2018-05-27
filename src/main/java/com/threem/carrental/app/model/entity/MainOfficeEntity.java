package com.threem.carrental.app.model.entity;

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
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "domain")
    private String domain;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

}
