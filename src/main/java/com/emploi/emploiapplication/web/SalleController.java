package com.emploi.emploiapplication.web;

import com.emploi.emploiapplication.entities.Salle;
import com.emploi.emploiapplication.services.SalleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/admin")
public class SalleController {
    private SalleService salleService;

    @PostMapping("/salle/ajouter")
    public Salle addSalle( @RequestBody Salle salle){
        return salleService.addSalle(salle);
    }

    @GetMapping("/salles")
    public List<Salle> salleList(){
        return salleService.salleList();
    }

    @GetMapping("/salle/getById")
    public Salle getSalleById(@RequestParam Long id){
        return salleService.getSalleById(id);
    }

    @GetMapping("/salle/chercher")
    public List<Salle> chercherSalle(@RequestParam String keyword){
        return salleService.chercherSalle(keyword);
    }

    @PutMapping("/salle")
    public Salle updateSalle(@RequestParam Long id ,
                             @RequestBody Salle salle){
        return salleService.updateSalle(id,salle);
    }

    @DeleteMapping("/salle/supprimer")
    public String deleteSalle(@RequestParam Long id){
        return salleService.deleteSalle(id);
    }


    @GetMapping("/salle/getSalleDeCours")
    public List<Salle> getAllSalleDECours(){
        return salleService.getSalleDeCours();
    }

    @GetMapping("/salle/getSalleTDetTP")
    public List<Salle> getAllSalleTDetTP(){
        return salleService.getSalleDeTDetTP();
    }

    @GetMapping("/salle/getSalleCoursDisponible")
    public List<Salle> getSalleCoursisponible(@RequestParam String jour,@RequestParam String periode){
        return salleService.getSalleCoursDisponibleParJouretPeriode(jour, periode);
    }

    @GetMapping("/salle/getSalleTdEtTpDisponible")
    public List<Salle> getSalleTdEtTPDisponible(@RequestParam String jour,@RequestParam String periode){
        return salleService.getSalleTdTPDisponibleParJouretPeriode(jour, periode);
    }
}
