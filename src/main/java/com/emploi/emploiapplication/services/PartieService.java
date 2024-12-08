package com.emploi.emploiapplication.services;

import com.emploi.emploiapplication.classes.PartiewithModule;
import com.emploi.emploiapplication.entities.Module;
import com.emploi.emploiapplication.entities.Partie;

import java.util.List;

public interface PartieService {
    Partie addPartie(Partie partie);

    List<Partie> PartieList();

    Partie getPartieById(Long id);

    List<Partie> chercherPartie(String keyword);

    Partie updatePartie(Long id, PartiewithModule partiewithModule);

    String deletePartie(Long id);

    List<Module> getModulesByIdPartie(Long idP);
}
