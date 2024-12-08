
package com.emploi.emploiapplication.web;

import com.emploi.emploiapplication.DTO.SeanceDTO;
import com.emploi.emploiapplication.entities.Seance;
import com.emploi.emploiapplication.repository.GroupeRepository;
import com.emploi.emploiapplication.services.SeanceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/admin")
@Slf4j
@AllArgsConstructor
public class SeanceController {
    private SeanceService seanceService;
    private GroupeRepository groupeRepository;


    @PostMapping("/seance/ajouter")
    public SeanceDTO addSeance(@RequestBody SeanceDTO seanceDTO){
        return seanceService.saveSeance(seanceDTO);
    }

    @GetMapping("/seance/getSeanceByGroupe")
    public List<Seance> getSeancesByGroupe(@RequestParam Long idGroupe){
        return seanceService.getSeanceByGroupe(idGroupe);
    }

    @GetMapping("/seance/getSeanceByEnseignant")
    public List<Seance> getSeancesByProf(@RequestParam Long idEns){
        return seanceService.getSeanceByProf(idEns);
    }

    @GetMapping("/seance/getSeanceBySection")
    public List<Seance> getSeancesBySection(@RequestParam Long idSection){
        return seanceService.getSeanceBySection(idSection);
    }
    @DeleteMapping("/seance/supprimer")
    public String deleteAllSeances(@RequestParam Long idSection){
        return seanceService.deleteAllBySection(idSection);
    }
    @GetMapping("/seance/getSeanceDeGroupe")
    public List<Seance> getSeanceDeGroupe(@RequestParam Long idGroupe ) {
        List<Seance> seances = seanceService.getSeanceByGroupe(idGroupe);
        Long idSection = groupeRepository.findSectionByGroupe(idGroupe);
        seances.addAll(seanceService.getSeanceBySectionANDGROUPENULL(idSection));
        return seances ;
    }
}
