package com.threem.carrental.app.model.outbound.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(name = "zip-code")
    private String zipCode;

    @Column(name = "country")
    private String country;

    @Column(name = "phone")
    private String phone;

    public AddressBranchEntity(String city, String street, String building, String zipCode, String country, String phone) {
        this.city = city;
        this.street = street;
        this.building = building;
        this.zipCode = zipCode;
        this.country = country;
        this.phone = phone;
    }
}
