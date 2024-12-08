package com.emploi.emploiapplication.DTO;

import com.emploi.emploiapplication.entities.Semestre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartieDTO {
    private Long idPartie;
    private String libellePartie;
    private Date dateDebut;
    private Date dateFin;
    private Semestre semestre;
    private List<ModuleDTO> moduleDTOS;
}
