package com.emploi.emploiapplication.web;

import com.emploi.emploiapplication.classes.PartiewithModule;
import com.emploi.emploiapplication.entities.Module;
import com.emploi.emploiapplication.entities.Partie;
import com.emploi.emploiapplication.entities.Semestre;
import com.emploi.emploiapplication.services.ModuleService;
import com.emploi.emploiapplication.services.PartieService;
import com.emploi.emploiapplication.services.SemestreService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/admin")
public class PartieController {
    PartieService partieService;
    SemestreService semestreService;
    ModuleService moduleService;

    @PostMapping("/partie/ajouter")
    public Partie addPartie(@RequestParam Long idSemestre,
                         @RequestBody PartiewithModule partiewithModule) {
        Partie partie = new Partie();
        List<Module> modules = new ArrayList<>();
        Semestre semestre = semestreService.findSemestreById(idSemestre);
        partie.setSemestre(semestre);
        Module module1=moduleService.getModuleById(partiewithModule.getIdM1());
        modules.add(module1);
        Module module2=moduleService.getModuleById(partiewithModule.getIdM2());
        modules.add(module2);
        Module module3=moduleService.getModuleById(partiewithModule.getIdM3());
        modules.add(module3);
        partie.setLibellePartie(partiewithModule.getLibellePartie());
        partie.setDateDebut(partiewithModule.getDateDebut());
        partie.setDateFin(partiewithModule.getDateFin());
        partie.setModules(modules);
        return partieService.addPartie(partie);
    }
    @GetMapping("/partie")
    public List<Partie> PartieList(){
        return partieService.PartieList();
    }

    @GetMapping("/partie/{id}")
    public Partie getPartieById(@RequestParam Long id){
        return partieService.getPartieById(id);
    }

    @GetMapping("/partie/chercher")
    public List<Partie> chercherPartie(@RequestParam String keyword){
        return partieService.chercherPartie(keyword);
    }
    @PutMapping("/partie/modifier")
    public Partie updatePartie(@RequestParam Long id ,
                             @RequestBody PartiewithModule partiewithModule){
        return partieService.updatePartie(id,partiewithModule);
    }

    @DeleteMapping("/partie/{id}")
    public String deletePartie(@RequestParam Long id){
        return partieService.deletePartie(id);
    }

    @GetMapping("/partie/getModules")
    public List<Module> ModulesByPartie(@RequestParam Long id) {
        return partieService.getModulesByIdPartie(id);
    }
}
