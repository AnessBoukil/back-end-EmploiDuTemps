package com.emploi.emploiapplication.services;

import com.emploi.emploiapplication.DTO.SeanceDTO;
import com.emploi.emploiapplication.entities.Seance;

import java.util.List;

public interface SeanceService {
    SeanceDTO saveSeance(SeanceDTO seanceDTO);
    List<Seance> getSeanceByGroupe(Long idG);

    List<Seance> getSeanceByProf(Long idProf);


    List<Seance> getSeanceBySection(Long idSection);

    List<Seance> getSeanceBySectionANDGROUPENULL(Long idSection);

    String deleteAllBySection(Long idSection);
}
