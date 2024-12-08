package com.emploi.emploiapplication.repository;

import com.emploi.emploiapplication.entities.Groupe;
import com.emploi.emploiapplication.entities.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupeRepository extends JpaRepository<Groupe,Long>{
    @Query("select g from Groupe g where g.libelleGroupe like %?1%")
    List<Groupe> findGroupeByKeyword(String keyword);

    @Query("select g from Groupe g where g.section.idSection=?1")
    List<Groupe> findAllBySection(Long idSection);

    @Query("select g.section.idSection from Groupe g where g.idGroupe=?1")
    Long findSectionByGroupe(Long idGroupe);
}
