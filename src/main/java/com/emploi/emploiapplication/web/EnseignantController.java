package com.emploi.emploiapplication.web;

import com.emploi.emploiapplication.DTO.EnseignantDTO;
import com.emploi.emploiapplication.entities.Enseignant;
import com.emploi.emploiapplication.services.EnseignantSevice;
import com.emploi.emploiapplication.settings.MailSenderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping(path = "/admin")
public class EnseignantController {
    private EnseignantSevice enseignantService;
    private MailSenderService mailSenderService;

    @PostMapping("/enseignant/ajouter")
    public EnseignantDTO createEnseignant(@RequestBody EnseignantDTO enseignantDTO){
        mailSenderService.sendAccountToProf(enseignantDTO);
        return enseignantService.addEnseignant(enseignantDTO);
    }

    @GetMapping("/enseignants")
    public List<EnseignantDTO> getALlEnseignants(){
        return enseignantService.getAllEnseignants();
    }

    @PutMapping("/enseignant/update")
    public EnseignantDTO updateEnseignant(@RequestParam Long idProf,@RequestBody EnseignantDTO updatedEnseignant) {
        return enseignantService.updateEnseignant(idProf,updatedEnseignant);
    }

    @DeleteMapping("/enseignant/{id}")
    public String deleteEnseignant(@PathVariable Long id) {
        return enseignantService.deleteEnseignant(id);
    }

    @GetMapping("/enseignant/chercher")
    public List<EnseignantDTO> searchEnseignantsByKeyword(@RequestParam String keyword){
        return enseignantService.chercherEnseignant(keyword);
    }

    @GetMapping("/enseignants/getByUsername")
    public EnseignantDTO getEnseignantByUserName(@RequestParam String username) {
        return enseignantService.getEnseignantByUsername(username);
    }

    @GetMapping("/enseignant/getEnseignant")
    public Enseignant findEnseignantById(@RequestParam Long idEns){
        return enseignantService.getEnseignantById(idEns);
    }

    @GetMapping("/enseignant/getDoctors")
    public List<EnseignantDTO> findEnseignantByType(){
        return enseignantService.getEnseignantDoctor();
    }

}
