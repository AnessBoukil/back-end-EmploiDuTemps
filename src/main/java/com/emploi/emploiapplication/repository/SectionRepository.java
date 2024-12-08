package com.emploi.emploiapplication.repository;

import com.emploi.emploiapplication.entities.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section,Long>{
    @Query("select s from Section s where s.libelleSection like %?1%")
    List<Section> findByKeyWord(String keyword);

}
