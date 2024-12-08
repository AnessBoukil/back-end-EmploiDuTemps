package com.emploi.emploiapplication.services;

import com.emploi.emploiapplication.DTO.EnseignantDTO;
import com.emploi.emploiapplication.entities.Enseignant;
import com.emploi.emploiapplication.entities.UserAccount;
import com.emploi.emploiapplication.mappers.MapperServiceImpl;
import com.emploi.emploiapplication.repository.EnseignantRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class EnseignantServiceImpl implements EnseignantSevice {
    private EnseignantRepository enseignantRepository;
    private MapperServiceImpl mapper ;


    @Override
    public List<EnseignantDTO> getAllEnseignants() {
        return enseignantRepository.findAll().stream().map(enseignant -> mapper.fromEnseignant(enseignant)).toList();
    }
    @Override
    public Enseignant getEnseignantById( Long id) {
        return enseignantRepository.findByIdUser(id);
    }

    @Override
    public EnseignantDTO addEnseignant(EnseignantDTO enseignantDTO) {
        Enseignant enseignant = mapper.fromEnseignantDTO(enseignantDTO);
        Enseignant savedenseignant = enseignantRepository.save(enseignant);
        UserAccount userAccount = mapper.fromUserAccountDTO(enseignantDTO.getUserAccountDTO());
        userAccount.setUser(savedenseignant);
        userAccount.setRole("ENSEIGNANT");
        savedenseignant.setUserAccount(userAccount);
        return mapper.fromEnseignant(enseignantRepository.save(enseignant));
    }
    @Override
    public EnseignantDTO updateEnseignant(Long idProf, EnseignantDTO enseignantDTO) {
        Enseignant enseignant = mapper.fromEnseignantDTO(enseignantDTO);
        enseignant.setIdUser(idProf);
        UserAccount userAccount = mapper.fromUserAccountDTO(enseignantDTO.getUserAccountDTO());
        userAccount.setId(enseignantDTO.getUserAccountDTO().getId());
        userAccount.setUser(enseignant);
        userAccount.setRole("ENSEIGNANT");
        enseignant.setUserAccount(userAccount);
        return mapper.fromEnseignant(enseignantRepository.save(enseignant));
    }
    @Override
    public String deleteEnseignant(Long id) {
        try {
            enseignantRepository.deleteById(id);
            return "l'operation est bien effetuee";
        }catch(Exception e){
            return e.getMessage();
        }

    }

    @Override
    public List<EnseignantDTO> chercherEnseignant(String keyword) {
        List<Enseignant> enseignants = enseignantRepository.findEnseignantByKeyword(keyword);
        return enseignants.stream().map(enseignant -> mapper.fromEnseignant(enseignant)).toList();
    }


    @Override
    public EnseignantDTO getEnseignantByUsername(String username) {
        return mapper.fromEnseignant(enseignantRepository.findByUsername(username));
    }
    @Override
    public List<EnseignantDTO> getEnseignantDoctor(){
        List<Enseignant> enseignants = enseignantRepository.findEnseignantByType();
        return  enseignants.stream().map(enseignant -> mapper.fromEnseignant(enseignant)).toList();
    }
}
