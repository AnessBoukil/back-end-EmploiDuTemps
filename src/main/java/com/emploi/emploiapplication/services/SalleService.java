package com.emploi.emploiapplication.services;

import com.emploi.emploiapplication.entities.Salle;

import java.util.List;

public interface SalleService {
    Salle addSalle(Salle salle);

    List<Salle> salleList();

    Salle getSalleById(Long id);

    List<Salle> chercherSalle(String keyword);

    Salle updateSalle(Long id, Salle salle);

    String deleteSalle(Long id);

    List<Salle> getSalleDeCours();

    List<Salle> getSalleDeTDetTP();

    List<Salle> getSalleCoursDisponibleParJouretPeriode(String jour, String periode);

    List<Salle> getSalleTdTPDisponibleParJouretPeriode(String jour, String periode);
}
