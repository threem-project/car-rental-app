package com.threem.carrental.installer.model.outbound.view;

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
public class InstallerView {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

}
