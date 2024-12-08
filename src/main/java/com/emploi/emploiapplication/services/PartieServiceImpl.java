package com.emploi.emploiapplication.services;

import com.emploi.emploiapplication.classes.PartiewithModule;
import com.emploi.emploiapplication.entities.Module;
import com.emploi.emploiapplication.entities.Partie;
import com.emploi.emploiapplication.repository.PartieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PartieServiceImpl implements PartieService{
    private PartieRepository partieRepository;
    private ModuleService moduleService;

    @Override
    public Partie addPartie(Partie partie) {
        return partieRepository.save(partie);
    }

    @Override
    public List<Partie> PartieList() {
        return partieRepository.findAll();
    }

    @Override
    public Partie getPartieById(Long id) {
        return partieRepository.findById(id).orElseThrow(()->new RuntimeException("la partie n'existe pas"));
    }

    @Override
    public List<Partie> chercherPartie(String keyword) {
        return partieRepository.findByKeyWord(keyword);
    }

    @Override
    public Partie updatePartie(Long idPartie, PartiewithModule partiewithModule) {
        Partie partie = new Partie();
        partie.setIdPartie(idPartie);
        List<Module> modules = new ArrayList<>();
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
        return partieRepository.save(partie);
    }

    @Override
    public String deletePartie(Long id) {
        try {
            partieRepository.deleteById(id);
            return "l'operation est bien effetuee";
        }catch(Exception e){
            return e.getMessage();
        }
    }

    @Override
    public List<Module> getModulesByIdPartie(Long idP) {
        return partieRepository.findModulesByPartie(idP);
    }
}
