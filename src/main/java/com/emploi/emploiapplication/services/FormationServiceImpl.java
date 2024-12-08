package com.emploi.emploiapplication.services;

import com.emploi.emploiapplication.entities.Formation;
import com.emploi.emploiapplication.repository.FormationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FormationServiceImpl implements FormationService {
    private FormationRepository formationRepository;
    @Override
    public Formation addFormation(Formation formation) {
        return formationRepository.save(formation);
    }

    @Override
    public List<Formation> getAllFormations() {
        return formationRepository.findAll();
    }

    @Override
    public Formation getFomationById(Long id) {
        return formationRepository.findById(id).orElseThrow(()->new RuntimeException("la formation n'existe pas"));
    }

    @Override
    public List<Formation> chercherFormation(String keyword) {
        return formationRepository.findByKeyWord(keyword);
    }

    @Override
    public Formation updateFormation(Long id, Formation formation) {
        formation.setIdFormation(id);
        return formationRepository.save(formation);
    }


    @Override
    public String deleteFormation(Long id) {
        try {
            formationRepository.deleteById(id);
            return "l'operation est bien effetuee";
        }catch(Exception e){
            return e.getMessage();
        }
    }
}
