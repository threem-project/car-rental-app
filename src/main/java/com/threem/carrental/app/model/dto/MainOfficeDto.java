package com.threem.carrental.app.model.dto;

import lombok.*;

/**
 * @author misza_lemko on 27.05.2018
 * @project car-rental-app
 */

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class MainOfficeDto {

    private Long id;

    private String name;

    private String domain;

    private String address;

    private String phone;

    private String email;
}
