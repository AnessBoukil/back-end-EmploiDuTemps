package com.emploi.emploiapplication.repository;

import com.emploi.emploiapplication.DTO.SeanceDTO;
import com.emploi.emploiapplication.entities.Seance;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeanceRepository extends JpaRepository<Seance,Long>{
    @Query("select e from Seance e where e.groupe.idGroupe=?1")
    List<Seance> findByGroupe(Long idGroupe);

    @Query("select e from Seance e where e.enseignant.idUser=?1")
    List<Seance> findByEnseignant(Long idEnseignant);

    @Query("select e from Seance e where e.section.idSection=?1")
    List<Seance> findBySection(Long idSection);


    @Modifying
    @Transactional
    @Query("DELETE FROM Seance d WHERE d.section.idSection= ?1")
    void deleteByIdSection(Long idSection);


    @Query("select e from Seance e where e.section.idSection=?1 and e.groupe.idGroupe is null")
    List<Seance> findBySectionREQ(Long idSection);
}
