package com.emploi.emploiapplication.services;

import com.emploi.emploiapplication.entities.Semestre;

import java.util.List;

public interface SemestreService {
    Semestre ajouterSemestre(Semestre semestre);

    List<Semestre> semestreList();

    Semestre findSemestreById(Long id);

    List<Semestre> findSemestreByKeyword(String keyword);

    Semestre updateSemestre(Long id, Semestre semestre);

    String deleteSemestre(Long id);

    List<Semestre> findSemestreByIdFormation(Long idF);
}
