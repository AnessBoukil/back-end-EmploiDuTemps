package com.emploi.emploiapplication.mappers;

import com.emploi.emploiapplication.DTO.*;
import com.emploi.emploiapplication.entities.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class MapperServiceImpl {

    public UserAccountDTO fromUserAccount(UserAccount userAccount) {
        UserAccountDTO userAccountDTO = new UserAccountDTO();
        BeanUtils.copyProperties(userAccount, userAccountDTO);
        return userAccountDTO;
    }

    public UserAccount fromUserAccountDTO(UserAccountDTO userAccountDTO) {
        UserAccount userAccount = new UserAccount();
        BeanUtils.copyProperties(userAccountDTO, userAccount);
        return userAccount;
    }
    public Enseignant fromEnseignantDTO(EnseignantDTO enseignantDTO){
        Enseignant enseignant = new Enseignant();
        BeanUtils.copyProperties(enseignantDTO,enseignant);
        return enseignant;
    }
    public EnseignantDTO fromEnseignant(Enseignant enseignant){
        EnseignantDTO enseignantDTO=new EnseignantDTO();
        BeanUtils.copyProperties(enseignant,enseignantDTO);
        enseignantDTO.setUserAccountDTO(fromUserAccount(enseignant.getUserAccount()));
        return enseignantDTO;
    }
    public Seance fromSeanceDTO(SeanceDTO seanceDTO){
        Seance seance = new Seance();
        BeanUtils.copyProperties(seanceDTO,seance);
        return seance;
    }
    public SeanceDTO fromSeance(Seance seance){
        SeanceDTO seanceDTO=new SeanceDTO();
        BeanUtils.copyProperties(seance,seanceDTO);
        seanceDTO.setGroupe(seance.getGroupe());
        seanceDTO.setEnseignant(seance.getEnseignant());
        seanceDTO.setModule(seance.getModule());
        seanceDTO.setSalle(seance.getSalle());
        seanceDTO.setSection(seance.getSection());
        return seanceDTO;
    }

}

