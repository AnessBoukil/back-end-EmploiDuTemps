package com.emploi.emploiapplication.web;

import com.emploi.emploiapplication.entities.Formation;
import com.emploi.emploiapplication.entities.Semestre;
import com.emploi.emploiapplication.services.FormationService;
import com.emploi.emploiapplication.services.SemestreService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/admin")
public class SemestreController {
    private SemestreService semestreService;
    private FormationService formationService;

    @PostMapping("/semestre/ajouter")
    public Semestre addSemestre(@RequestParam Long idFormation,@RequestBody Semestre semestre){
        Formation formation = formationService.getFomationById(idFormation);
        semestre.setFormation(formation);
        return semestreService.ajouterSemestre(semestre);
    }

    @GetMapping("/semestres")
    public List<Semestre> semestreList(){
        return semestreService.semestreList();
    }

    @GetMapping("/semstre/{id}")
    public Semestre getSemestreById(@RequestParam Long id){
        return semestreService.findSemestreById(id);
    }

    @GetMapping("/semestre/chercher")
    public List<Semestre> chercherSemestre(@RequestParam String keyword){
        return semestreService.findSemestreByKeyword(keyword);
    }

    @PutMapping("/semestre/modifier")
    public Semestre updateSemestre(@RequestParam Long id,
                                   @RequestBody Semestre semestre){
        return semestreService.updateSemestre(id,semestre);
    }

    @GetMapping("/semestre/getByFormation")
    public List<Semestre> loadSemestres(@RequestParam Long idF){
        return semestreService.findSemestreByIdFormation(idF);
    }

    @DeleteMapping("/semestre/{id}")
    public String deleteSemestre(@RequestParam Long id){
        return semestreService.deleteSemestre(id);
    }

}
