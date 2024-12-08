package com.emploi.emploiapplication.services;

import com.emploi.emploiapplication.entities.Semestre;
import com.emploi.emploiapplication.repository.SemestreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SemestreServiceImpl implements SemestreService{
    private SemestreRepository semestreRepository;
    @Override
    public Semestre ajouterSemestre(Semestre semestre) {
        return semestreRepository.save(semestre);
    }

    @Override
    public List<Semestre> semestreList() {
        return semestreRepository.findAll();
    }

    @Override
    public Semestre findSemestreById(Long id) {
        return semestreRepository.findById(id).orElseThrow(()-> new RuntimeException("la semestre n'existe pas"));
    }

    @Override
    public List<Semestre> findSemestreByKeyword(String keyword) {
        return semestreRepository.findByKeyWord();
    }

    @Override
    public Semestre updateSemestre(Long id, Semestre semestre) {
        semestre.setIdSemestre(id);
        return semestreRepository.save(semestre);
    }
    @Override
    public String deleteSemestre(Long id) {
        try{
            semestreRepository.deleteById(id);
            return "l'operation est bien effectuee";
        }catch(Exception e){
            return e.getMessage() ;
        }
    }
    @Override
    public List<Semestre> findSemestreByIdFormation(Long idF){
        return semestreRepository.findAllByFormation(idF);
    }

}
