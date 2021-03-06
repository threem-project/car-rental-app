package com.threem.carrental.app.model.entity;

import lombok.*;

import javax.persistence.*;

/**
 * @author misza_lemko on 11.05.2018
 * @project car-rental-app
 */

@Entity
@Table(name = "address_branch")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class AddressBranchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "building")
    private String building;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "country")
    private String country;

    @Column(name = "phone")
    private String phone;

}
