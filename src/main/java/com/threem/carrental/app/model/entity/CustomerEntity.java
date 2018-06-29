package com.threem.carrental.app.model.entity;

import com.threem.carrental.app.model.entity.enumTypes.CustomerAccountStatusEnum;
import com.threem.carrental.app.model.entity.enumTypes.CustomerTypeEnum;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * @author misza_lemko on 11.05.2018
 * @project car-rental-app
 */


@Entity
@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "pesel")
    private String pesel;

    @Column(name = "document_id")
    private String documentId;

    @Column(name = "driver_license")
    private String driverLicense;

    @Column(name = "passport_id")
    private String passportId;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private CustomerTypeEnum status;

    @Column(name = "account_status")
    @Enumerated(EnumType.STRING)
    private CustomerAccountStatusEnum accountStatus;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private AddressCustomerEntity address;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "customer")
    private List<BookingEntity> bookings;

}
