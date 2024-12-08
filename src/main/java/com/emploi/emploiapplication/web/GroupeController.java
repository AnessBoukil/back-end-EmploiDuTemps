package com.emploi.emploiapplication.web;

import com.emploi.emploiapplication.entities.Groupe;
import com.emploi.emploiapplication.entities.Section;
import com.emploi.emploiapplication.services.GroupeService;
import com.emploi.emploiapplication.services.SectionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin")
@RestController
@Slf4j
@AllArgsConstructor
public class GroupeController {
    private GroupeService groupeService ;
    private SectionService sectionService;

    @PostMapping ("/groupe/ajouter")
    public Groupe addGroupe(@RequestParam Long idSection ,
                            @RequestBody Groupe groupe){
        Section section = sectionService.getSectionById(idSection);
        groupe.setSection(section);
        return groupeService.addGroupe(groupe);
    }

    @GetMapping("/groupes")
    public List<Groupe> groupesList(){
        return groupeService.getAllGroupes();
    }

    @GetMapping("/gourpe/getGroupe")
    public Groupe getGroupe(@RequestParam Long id){
        return groupeService.getGroupeById(id);
    }

    @PutMapping("/groupe/modifier")
    public Groupe updateGroupe(Long id ,Groupe groupe){
        return groupeService.updateGroupe(id,groupe);
    }

    @GetMapping("/groupe")
    public List<Groupe> chercherGroupe(@RequestParam String keyword){
        return groupeService.chercherGroupe(keyword);
    }

    @DeleteMapping("/groupe/{id}")
    public String deleteGroupe(@RequestParam Long id){
        return groupeService.deleteGroupe(id);
    }

    @GetMapping("/groupe/groupeBySection")
    public List<Groupe> getGroupBYSection(@RequestParam Long idSection){
        return groupeService.findGroupeBySection(idSection);
    }
}
