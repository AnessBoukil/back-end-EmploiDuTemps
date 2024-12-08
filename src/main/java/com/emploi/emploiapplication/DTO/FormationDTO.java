package com.emploi.emploiapplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormationDTO {
    private Long idFormation;
    private String libelleFormation;
    private String parcours;
}
