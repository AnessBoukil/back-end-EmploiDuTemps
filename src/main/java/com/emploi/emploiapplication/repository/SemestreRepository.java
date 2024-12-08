package com.emploi.emploiapplication.repository;

import com.emploi.emploiapplication.entities.Semestre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SemestreRepository extends JpaRepository<Semestre,Long>{
    @Query("select s from Semestre s where s.session like %?1%")
    List<Semestre> findByKeyWord();

    @Query("select s from Semestre s where s.formation.idFormation=?1")
    List<Semestre> findAllByFormation(Long idFormation);
}
