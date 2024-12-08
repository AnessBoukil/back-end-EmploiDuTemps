package com.emploi.emploiapplication.repository;

import com.emploi.emploiapplication.entities.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EnseignantRepository extends JpaRepository<Enseignant,Long> {
    @Query("SELECT e FROM Enseignant e WHERE (e.nom LIKE %?1% OR e.prenom LIKE %?1%)")
    List<Enseignant> findEnseignantByKeyword(String keyword);

    @Query("select e from Enseignant e where e.userAccount.username = ?1")
    Enseignant findByUsername(String username);
    Enseignant findByIdUser(Long id);

    @Query("select e from Enseignant e where e.type = 'Permanant'")
    List<Enseignant> findEnseignantByType();


}
