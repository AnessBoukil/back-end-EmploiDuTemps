package com.emploi.emploiapplication.repository;

import com.emploi.emploiapplication.entities.Disponibilite;
import com.emploi.emploiapplication.entities.Enseignant;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface DisponibiliteRepository extends JpaRepository<Disponibilite,Long>{
    @Query("select d from Disponibilite d where d.enseignant.idUser=?1")
    List<Disponibilite> getDisponibiliteByidProf(Long idProf);
    @Query("SELECT e.enseignant FROM Disponibilite e LEFT JOIN Seance s ON e.enseignant = s.enseignant AND e.jour = s.jourSeance AND e.periode = s.periode WHERE e.jour = ?1 AND e.periode = ?2 AND s.enseignant IS NULL and e.enseignant.type='Permanent'")
    List<Enseignant> getAllProfDisponible(String jour,String date);
    @Query("SELECT e.enseignant FROM Disponibilite e LEFT JOIN Seance s ON e.enseignant = s.enseignant AND e.jour = s.jourSeance AND e.periode = s.periode WHERE e.jour = ?1 AND e.periode = ?2 AND s.enseignant IS NULL")
    List<Enseignant> getDoctorAndDoctorantDisponible(String jour,String date);
    @Modifying
    @Transactional
    @Query("DELETE FROM Disponibilite d WHERE d.enseignant.idUser = ?1")
    void deleteAllByEnseignantId(Long enseignantId);
}