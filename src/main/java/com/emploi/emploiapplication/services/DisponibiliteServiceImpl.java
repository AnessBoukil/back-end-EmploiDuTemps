package com.emploi.emploiapplication.services;

import com.emploi.emploiapplication.entities.Disponibilite;
import com.emploi.emploiapplication.entities.Enseignant;
import com.emploi.emploiapplication.repository.DisponibiliteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DisponibiliteServiceImpl implements DisponibiliteService{

    private DisponibiliteRepository disponibiliteRepository;
    @Override
    public List<Disponibilite> getNonDisponibilites() {
        return disponibiliteRepository.findAll();
    }


    @Override
    public Disponibilite addNonDisponibilite(Disponibilite nonDisponibilite) {
        return disponibiliteRepository.save(nonDisponibilite);
    }

    @Override
    public Disponibilite updateNonDisponibilite(Long id, Disponibilite updatedNonDisponibilite) {
        updatedNonDisponibilite.setIdDisp(id);
        return disponibiliteRepository.save(updatedNonDisponibilite);
    }
    @Override
    public List<Disponibilite> getNonDisponibiliteByIdProf(Long idProf) {
        return disponibiliteRepository.getDisponibiliteByidProf(idProf);
    }
    @Override
    public List<Enseignant> getAllProfDispoParJourEtPeriode(String jour,String periode){
        return disponibiliteRepository.getAllProfDisponible(jour,periode);
    }

    @Override
    public List<Enseignant> getAllDOCTORandDPCTORANTDispoParJourEtPeriode(String jour,String periode){
        return disponibiliteRepository.getDoctorAndDoctorantDisponible(jour,periode);
    }
    @Override
    public String deleteDispoByProf(Long idProf){
        try{
            disponibiliteRepository.deleteAllByEnseignantId(idProf);
            return "l'operation est bien effectuee";
        }catch(Exception e){
            return e.getMessage() ;
        }
    }
}
