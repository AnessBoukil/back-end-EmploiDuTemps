package com.emploi.emploiapplication.services;

import com.emploi.emploiapplication.entities.Disponibilite;
import com.emploi.emploiapplication.entities.Enseignant;

import java.util.List;

public interface DisponibiliteService {
    List<Disponibilite> getNonDisponibilites();

    Disponibilite addNonDisponibilite(Disponibilite nonDisponibilite);

    Disponibilite updateNonDisponibilite(Long id, Disponibilite updatedNonDisponibilite);

    List<Disponibilite> getNonDisponibiliteByIdProf(Long idProf);

    List<Enseignant> getAllProfDispoParJourEtPeriode(String jour, String periode);

    List<Enseignant> getAllDOCTORandDPCTORANTDispoParJourEtPeriode(String jour, String periode);

    String deleteDispoByProf(Long idProf);
}
