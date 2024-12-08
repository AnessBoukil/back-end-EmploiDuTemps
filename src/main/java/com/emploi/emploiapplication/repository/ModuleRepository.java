package com.emploi.emploiapplication.repository;

import com.emploi.emploiapplication.entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ModuleRepository extends JpaRepository<Module,Long>{
    @Query("select m from Module m where m.libelleModule like %?1%")
    List<Module> chercherModuleByKeyWord(String keyword);
}
