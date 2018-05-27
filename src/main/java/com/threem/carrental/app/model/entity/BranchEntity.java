package com.threem.carrental.app.model.entity;
import com.threem.carrental.app.model.entity.enumTypes.BranchStatusEnum;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * @author misza_lemko on 11.05.2018
 * @project car-rental-app
 */


@Entity
@Table(name = "branch")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class BranchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private AddressBranchEntity address;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_office_id")
    private MainOfficeEntity mainOffice;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private BranchStatusEnum status;

}
