package com.threem.carrental.app.model.outbound.entity;

import com.threem.carrental.app.model.outbound.entity.enumTypes.EmployeeRoleEnum;
import com.threem.carrental.app.model.outbound.entity.enumTypes.EmployeeStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author marek_j on 2018-05-10
 */

@Entity
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    private EmployeeRoleEnum role;

    @Transient
    private Integer branch; //this field has to refactored as entity

    @Enumerated(EnumType.STRING)
    private EmployeeStatusEnum status;

    @Column(name = "password")
    private String password;
}
