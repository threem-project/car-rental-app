package com.threem.carrental.app.model.outbound.entity;

import com.threem.carrental.app.model.outbound.entity.enumTypes.BookingStatusEnum;


import com.threem.carrental.app.model.outbound.entity.enumTypes.PaymentStatusEnum;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.math.BigDecimal;

/**
 * @author misza_lemko on 11.05.2018
 * @project car-rental-app
 */

@Entity
@Table(name = "booking_event")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookingEventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "need_payment")
    private BigDecimal needPayment;

    @Column(name = "payment")
    private BigDecimal payment;

    @Column(name = "mileage")
    private Integer mileage;

    @Column(name = "payment_status")
    @Enumerated(EnumType.STRING)
    private PaymentStatusEnum paymentStatus;

    @Column(name = "booking_status")
    @Enumerated(EnumType.STRING)
    private BookingStatusEnum bookingStatus;

    @Column(name = "comments")
    private String comments;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    private BookingEntity booking;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;

}
