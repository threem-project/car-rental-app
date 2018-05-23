package com.threem.carrental.app.model.entity;

import com.threem.carrental.app.model.entity.enumTypes.EmployeeRoleEnum;
import com.threem.carrental.app.model.entity.enumTypes.EmployeeStatusEnum;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * @author marek_j on 2018-05-10
 */

@Entity
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private EmployeeRoleEnum role;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EmployeeStatusEnum status;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    private BranchEntity branch;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "employee")
    private List<BookingEntity> bookings;


}