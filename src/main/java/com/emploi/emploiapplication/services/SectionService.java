package com.emploi.emploiapplication.services;

import com.emploi.emploiapplication.entities.Section;

import java.util.List;

public interface SectionService {
    Section ajouterSection(Section section);

    List<Section> sectionList();

    Section getSectionById(Long id);

    List<Section> chercherSection(String keyword);

    Section updateSection(Long id, Section section);

    String deleteSection(Long id);

}
