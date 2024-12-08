package com.emploi.emploiapplication.repository;

import com.emploi.emploiapplication.entities.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface  FormationRepository extends JpaRepository<Formation,Long>{
    @Query("select f from Formation f where f.libelleFormation like %?1%")
    List<Formation> findByKeyWord(String keyword);
}
