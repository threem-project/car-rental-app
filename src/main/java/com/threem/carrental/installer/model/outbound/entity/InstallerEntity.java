package com.threem.carrental.installer.model.outbound.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author marek_j on 2018-05-10
 */
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "installation_status")
@Builder
public class InstallerEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "installation_date")
    private LocalDateTime installationDate;

    @Column(name = "license_key")
    private String license = "for_future_usage";

    @Column(name = "crc")
    private String crc = "for_future_usage";

    @Enumerated(EnumType.STRING)
    private InstallationStatusEnum installationStatus;

}
