package com.emploi.emploiapplication.repository;

import com.emploi.emploiapplication.entities.Module;
import com.emploi.emploiapplication.entities.Partie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PartieRepository extends JpaRepository<Partie,Long>{
    @Query("select s from Partie s where s.libellePartie = ?1")
    List<Partie> findByKeyWord(String keyword);

    @Query("select p.modules from Partie p where p.idPartie =?1")
    List<Module> findModulesByPartie(Long idP);
}
