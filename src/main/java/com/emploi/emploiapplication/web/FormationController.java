package com.emploi.emploiapplication.web;

import com.emploi.emploiapplication.entities.Formation;
import com.emploi.emploiapplication.services.FormationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin")
@RestController
@Slf4j
@AllArgsConstructor
public class FormationController {
   private FormationService formationService;

    @PostMapping("/formation/ajouter")
    public Formation createFormation(@RequestBody Formation formation){
        return formationService.addFormation(formation);
    }

    @GetMapping("/formations")
    public List<Formation> FormationList (){
        return formationService.getAllFormations();
    }

    @GetMapping("/formation/{id}")
    public Formation getFormationById(@PathVariable Long id){
        return formationService.getFomationById(id);
    }

    @GetMapping("/formation/chercher")
    public List<Formation> chercherFormation(@RequestParam String keyword){
        return formationService.chercherFormation(keyword);
    }

    @PutMapping("/fomation/modifier")
    public Formation updateFormation(@RequestParam Long id,
                               @RequestBody Formation formation){
        return formationService.updateFormation(id,formation);
    }

    @DeleteMapping("/formation/{id}")
    public String deleteFormation(@PathVariable Long id){
        return formationService.deleteFormation(id);
    }
}
