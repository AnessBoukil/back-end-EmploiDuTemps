package com.emploi.emploiapplication.web;

import com.emploi.emploiapplication.entities.Partie;
import com.emploi.emploiapplication.entities.Section;
import com.emploi.emploiapplication.services.PartieService;
import com.emploi.emploiapplication.services.SectionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/admin")
public class SectionController {
    private SectionService sectionService;
    private PartieService partieService;

    @PostMapping("/section/ajouter")
    public Section addSection(@RequestParam Long idPartie,
                              @RequestBody Section section){
        Partie partie=partieService.getPartieById(idPartie);
        section.setPartie(partie);
        return sectionService.ajouterSection(section);
    }
    @GetMapping("/section/getSection")
    public Section getSectionById(@RequestParam Long idSection){
        return sectionService.getSectionById(idSection);
    }

    @GetMapping("/sections")
    public List<Section> sectionList(){
        return sectionService.sectionList();
    }

    @GetMapping("/section/chercher")
    public List<Section> chercherSection(@RequestParam String keyword){
        return sectionService.chercherSection(keyword);
    }

    @PutMapping("/section")
    public Section updateSection(@RequestParam Long id,
                                 @RequestBody Section section){
        return sectionService.updateSection(id,section);
    }

    @DeleteMapping("/section/{id}")
    public String deleteSection(@RequestParam Long id){
        return sectionService.deleteSection(id);
    }
}