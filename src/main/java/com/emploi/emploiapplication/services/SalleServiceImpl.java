package com.emploi.emploiapplication.services;

import com.emploi.emploiapplication.entities.Salle;
import com.emploi.emploiapplication.repository.SalleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class  SalleServiceImpl implements SalleService {
    private SalleRepository salleRepository;
    @Override
    public Salle addSalle(Salle salle) {
        return salleRepository.save(salle);
    }

    @Override
    public List<Salle> salleList() {
        return salleRepository.findAll();
    }

    @Override
    public Salle getSalleById(Long id) {
        return salleRepository.findById(id).orElseThrow(() -> new RuntimeException("Cette salle n'existe pas."));
    }

    @Override
    public List<Salle> chercherSalle(String keyword) {
        return salleRepository.findSalleByKeyWord(keyword);
    }

    @Override
    public Salle updateSalle(Long id, Salle salle) {
        salle.setIdSalle(id);
        return salleRepository.save(salle) ;
    }

    @Override
    public String deleteSalle(Long id) {
        try{
            salleRepository.deleteById(id);
            return "la suppression est bien effectuee";
        }catch(Exception e){
            return e.getMessage() ;
        }
    }
    @Override
    public List<Salle> getSalleDeCours(){
        return salleRepository.findSalleDeCours();
    }

    @Override
    public List<Salle> getSalleDeTDetTP(){
        return salleRepository.findSalleDeTdEtTP();
    }
    @Override
    public List<Salle> getSalleCoursDisponibleParJouretPeriode(String jour, String periode){
        return salleRepository.getSalleCoursDispoParJourEtPeriode(jour, periode);
    }

    @Override
    public List<Salle> getSalleTdTPDisponibleParJouretPeriode(String jour, String periode) {
        return salleRepository.getSalleTdTpDispoParJourEtPeriode(jour, periode);
    }
}
