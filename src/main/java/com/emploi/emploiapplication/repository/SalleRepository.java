package com.emploi.emploiapplication.repository;

import com.emploi.emploiapplication.entities.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SalleRepository extends JpaRepository<Salle,Long>{
    List<Salle> findSalleByIdSalle(Long IdSalle);

    @Query("select s from Salle s where s.libelleSalle like %?1%")
    List<Salle> findSalleByKeyWord(String keyword);
    @Query("select s from Salle s where s.typeSalle= 'Amphi' or s.typeSalle='Salle de Cours'")
    List<Salle> findSalleDeCours();

    @Query("select s from Salle s where s.typeSalle= 'Salle de TD' or s.typeSalle='Salle de TP'")
    List<Salle> findSalleDeTdEtTP();

    @Query("SELECT s FROM Salle s WHERE s.typeSalle IN ('Amphi', 'Salle de Cours') AND s.idSalle NOT IN ( SELECT e.salle.idSalle FROM Seance e WHERE e.jourSeance = ?1 AND e.periode = ?2 )")
    List<Salle> getSalleCoursDispoParJourEtPeriode(String jour, String periode);

    @Query("SELECT s FROM Salle s WHERE s.typeSalle IN ('Salle de TD', 'Salle de TP') AND s.idSalle NOT IN ( SELECT e.salle.idSalle FROM Seance e WHERE e.jourSeance = ?1 AND e.periode = ?2 )")
    List<Salle> getSalleTdTpDispoParJourEtPeriode(String jour, String periode);
}
