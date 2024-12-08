package com.emploi.emploiapplication.web;

import com.emploi.emploiapplication.entities.Disponibilite;
import com.emploi.emploiapplication.entities.Enseignant;
import com.emploi.emploiapplication.services.DisponibiliteService;
import com.emploi.emploiapplication.services.EnseignantSevice;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/admin")
public class DisponibliteController {
    private final DisponibiliteService disponibiliteService;
    private EnseignantSevice enseignantSevice;

    @GetMapping("/disponibilites")
    public List<Disponibilite> getAllDisponibilites() {
        return disponibiliteService.getNonDisponibilites();
    }

    @GetMapping("/disponibilite/{id}")
    public List<Disponibilite> getDisponibiliteByIdProf(@RequestParam  Long idProf) {
        return disponibiliteService.getNonDisponibiliteByIdProf(idProf);
    }

    @PostMapping("/disponibilite/ajouter")
    public Disponibilite createDisponibilite(@RequestParam Long idProf,
                                             @RequestBody Disponibilite nonDisponibilite) {
        Enseignant enseignant = enseignantSevice.getEnseignantById(idProf);
        nonDisponibilite.setEnseignant(enseignant);
        return disponibiliteService.addNonDisponibilite(nonDisponibilite);
    }

    @PutMapping("/disponibilite/{id}")
    public Disponibilite updateDisponibilite(@PathVariable Long id, @RequestBody Disponibilite updatedNonDisponibilite) {
        return disponibiliteService.updateNonDisponibilite(id, updatedNonDisponibilite);
    }

    @DeleteMapping("/disponibilites/supprimer")
    public String deleteDisponibilite(@RequestParam Long idProf) {
        return disponibiliteService.deleteDispoByProf(idProf);
    }

    @GetMapping("/disponibilite/getProfDispoParJour")
    public List<Enseignant> getProfDispo(@RequestParam String jour,@RequestParam String periode){
        return disponibiliteService.getAllProfDispoParJourEtPeriode(jour, periode);
    }

    @GetMapping("/disponibilite/getALLPROFDispoParJour")
    public List<Enseignant> getDoctorANDDoctorantDispo(@RequestParam String jour,@RequestParam String periode){
        return disponibiliteService.getAllDOCTORandDPCTORANTDispoParJourEtPeriode(jour, periode);
    }
}
