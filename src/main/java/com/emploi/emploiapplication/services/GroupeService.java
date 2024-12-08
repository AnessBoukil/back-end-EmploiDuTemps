package com.emploi.emploiapplication.services;

import com.emploi.emploiapplication.entities.Groupe;

import java.util.List;

public interface GroupeService {
    Groupe addGroupe(Groupe groupe);

    List<Groupe> getAllGroupes();

    Groupe getGroupeById(Long id);

    Groupe updateGroupe(Long id, Groupe groupe);

    String deleteGroupe(Long id);

    List<Groupe> chercherGroupe(String keyword);

    List<Groupe> findGroupeBySection(Long idSection);

}
