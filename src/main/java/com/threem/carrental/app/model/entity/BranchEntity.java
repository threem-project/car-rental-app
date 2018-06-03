package com.threem.carrental.app.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.threem.carrental.app.model.entity.enumTypes.BranchStatusEnum;
import lombok.*;

import javax.persistence.*;

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

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private AddressBranchEntity address;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private BranchStatusEnum status;

}
