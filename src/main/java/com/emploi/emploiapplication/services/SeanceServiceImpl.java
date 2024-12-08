package com.emploi.emploiapplication.services;

import com.emploi.emploiapplication.DTO.SeanceDTO;
import com.emploi.emploiapplication.entities.*;
import com.emploi.emploiapplication.entities.Module;
import com.emploi.emploiapplication.mappers.MapperServiceImpl;
import com.emploi.emploiapplication.repository.SeanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SeanceServiceImpl implements SeanceService{
    private SeanceRepository seanceRepository;
    private MapperServiceImpl mapper;
    private EnseignantSevice enseignantSevice;
    private ModuleService moduleService;
    private SalleService salleService;
    private SectionService sectionService;
    private GroupeService groupeService;
    @Override
    public SeanceDTO saveSeance(SeanceDTO seanceDTO){
        Seance seance = mapper.fromSeanceDTO(seanceDTO);
        Seance seance1=new Seance();
        Seance savedSeance = seanceRepository.save(seance);
        Enseignant enseignant = enseignantSevice.getEnseignantById(seanceDTO.getEnseignant().getIdUser());
        Module module = moduleService.getModuleById(seanceDTO.getModule().getIdModule());
        Salle salle =salleService.getSalleById(seanceDTO.getSalle().getIdSalle());
        Section section = sectionService.getSectionById(seanceDTO.getIdSection());
        Groupe groupe= groupeService.getGroupeById(seanceDTO.getGroupe().getIdGroupe());
        seance1.setIdSeance(savedSeance.getIdSeance());
        seance1.setJourSeance(savedSeance.getJourSeance());
        seance1.setPeriode(savedSeance.getPeriode());
        seance1.setTypeSeance(savedSeance.getTypeSeance());
        seance1.setEnseignant(enseignant);
        seance1.setGroupe(groupe);
        seance1.setModule(module);
        seance1.setSalle(salle);
        seance1.setSection(section);
        return mapper.fromSeance(seanceRepository.save(seance1));
    }

    @Override
    public List<Seance> getSeanceByGroupe(Long idG) {
        return seanceRepository.findByGroupe(idG);
    }
    @Override
    public List<Seance> getSeanceByProf(Long idProf) {
        return seanceRepository.findByEnseignant(idProf);
    }

    @Override
    public List<Seance> getSeanceBySection(Long idSection){
        return seanceRepository.findBySection(idSection);
    }

    @Override
    public List<Seance> getSeanceBySectionANDGROUPENULL(Long idSection){
        return seanceRepository.findBySectionREQ(idSection);
    }

    @Override
    public String deleteAllBySection(Long idSection) {
        try {
            seanceRepository.deleteByIdSection(idSection);
            return "l'operation est bien effetuee";
        }catch(Exception e){
            return e.getMessage();
        }    }
}
