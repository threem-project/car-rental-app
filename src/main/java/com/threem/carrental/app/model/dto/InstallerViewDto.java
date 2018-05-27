package com.threem.carrental.app.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * @author marek_j on 2018-05-10
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter (AccessLevel.PRIVATE)
@Builder
public class InstallerViewDto {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

}
