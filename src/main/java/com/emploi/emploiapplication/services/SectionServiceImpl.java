package com.emploi.emploiapplication.services;

import com.emploi.emploiapplication.entities.Section;
import com.emploi.emploiapplication.repository.SectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SectionServiceImpl implements SectionService{
    SectionRepository sectionRepository;
    @Override
    public Section ajouterSection(Section section) {
        return sectionRepository.save(section);
    }

    @Override
    public List<Section> sectionList() {
        return sectionRepository.findAll();
    }

    @Override
    public Section getSectionById(Long id) {
        return sectionRepository.findById(id).orElseThrow(()->new RuntimeException("la section n'existe pas"));
    }

    @Override
    public List<Section> chercherSection(String keyword) {
        return sectionRepository.findByKeyWord(keyword);
    }

    @Override
    public Section updateSection(Long id, Section section) {
        section.setIdSection(id);
        return sectionRepository.save(section);
    }

    @Override
    public String deleteSection(Long id) {
        try {
          sectionRepository.deleteById(id);
          return "la suppression effectuee";
        }catch (Exception e ){
            return e.getMessage();
        }
    }

}
