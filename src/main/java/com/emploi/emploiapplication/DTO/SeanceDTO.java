package com.emploi.emploiapplication.DTO;

import com.emploi.emploiapplication.entities.*;
import com.emploi.emploiapplication.entities.Module;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SeanceDTO {
    private Long IdSeance;
    private String typeSeance;
    private String periode;
    private String jourSeance ;
    private Salle salle;
    private Enseignant enseignant;
    private Module module;
    private Section Section;
    private Long idSection;
    private Groupe groupe;
}

