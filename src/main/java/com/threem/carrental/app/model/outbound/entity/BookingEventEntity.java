package com.threem.carrental.app.model.outbound.entity;

import com.threem.carrental.app.model.outbound.entity.enumTypes.BookingStatusEnum;


import com.threem.carrental.app.model.outbound.entity.enumTypes.PaymentStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class BookingEventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

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
    private PaymentStatusEnum paymentStatus;

    @Column(name = "booking_status")
    private BookingStatusEnum bookingStatus;

    @Column(name = "comments")
    private String comments;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    private BookingEntity booking;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;

    public BookingEventEntity(Date date, BigDecimal needPayment, BigDecimal payment, Integer mileage,
                              PaymentStatusEnum paymentStatus, BookingStatusEnum bookingStatus, String comments)
    {
        this.date = date;
        this.needPayment = needPayment;
        this.payment = payment;
        this.mileage = mileage;
        this.paymentStatus = paymentStatus;
        this.bookingStatus = bookingStatus;
        this.comments = comments;
    }
}
