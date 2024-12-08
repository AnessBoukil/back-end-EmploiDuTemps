package com.emploi.emploiapplication.services;

import com.emploi.emploiapplication.entities.Groupe;
import com.emploi.emploiapplication.repository.GroupeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GroupeServiceImpl implements GroupeService{
    GroupeRepository groupeRepository;
    @Override
    public Groupe addGroupe(Groupe groupe) {
        return groupeRepository.save(groupe);
    }

    @Override
    public List<Groupe> getAllGroupes() {
        return groupeRepository.findAll();
    }

    @Override
    public Groupe getGroupeById(Long id) {
        return groupeRepository.findById(id).orElseThrow(() -> new RuntimeException("Ce gourpe n'existe pas."));
    }

    @Override
    public Groupe updateGroupe(Long id, Groupe groupe) {
        groupe.setIdGroupe(id);
        return groupeRepository.save(groupe);
    }

    @Override
    public String deleteGroupe(Long id) {
        try{
            groupeRepository.deleteById(id);
            return "l'operation est bien effectuee";
        }catch(Exception e){
            return e.getMessage() ;
        }
    }

    @Override
    public List<Groupe> chercherGroupe(String keyword) {
        return groupeRepository.findGroupeByKeyword(keyword);
    }

    @Override
    public List<Groupe> findGroupeBySection(Long idSection) {
        return groupeRepository.findAllBySection(idSection);
    }

}
