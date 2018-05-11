package com.threem.carrental.app.model.outbound.entity;

import com.threem.carrental.app.model.outbound.entity.enumTypes.BookingStatusEnum;

/**
 * @author misza_lemko on 11.05.2018
 * @project car-rental-app
 */

import com.threem.carrental.app.model.outbound.entity.enumTypes.PaymentStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "booking")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="creation_date")
    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @Column(name = "date_from")
    @Temporal(TemporalType.DATE)
    private Date dateFrom;

    @Column(name = "date_to")
    @Temporal(TemporalType.DATE)
    private Date dateTo;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "payment_status")
    @Enumerated(EnumType.STRING)
    private PaymentStatusEnum paymentStatus;

    @Column(name = "booking_status")
    @Enumerated(EnumType.STRING)
    private BookingStatusEnum bookingStatus;

    @Column(name = "comments")
    private String comments;

    @Column(name = "date_return")
    @Temporal(TemporalType.DATE)
    private Date dateReturn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private CarEntity car;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "booking_branch",
            joinColumns ={@JoinColumn(name = "booking_id")},
            inverseJoinColumns = {@JoinColumn(name = "branch_id")}
    )
    private List<BranchEntity> branches;

    public BookingEntity(Date creationDate, Date dateFrom, Date dateTo,
                         BigDecimal price, PaymentStatusEnum paymentStatus,
                         BookingStatusEnum bookingStatus, String comments, Date dateReturn)
    {
        this.creationDate = creationDate;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.price = price;
        this.paymentStatus = paymentStatus;
        this.bookingStatus = bookingStatus;
        this.comments = comments;
        this.dateReturn = dateReturn;
    }
}
