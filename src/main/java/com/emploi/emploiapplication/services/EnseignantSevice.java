package com.emploi.emploiapplication.services;

import com.emploi.emploiapplication.DTO.EnseignantDTO;
import com.emploi.emploiapplication.entities.Enseignant;

import java.util.List;

public interface EnseignantSevice {
    List<EnseignantDTO> getAllEnseignants();
    Enseignant getEnseignantById(Long id);
    EnseignantDTO addEnseignant(EnseignantDTO enseignantDTO);

    EnseignantDTO updateEnseignant(Long idprof,EnseignantDTO enseignantDTO);

    String deleteEnseignant(Long id);

    List<EnseignantDTO> chercherEnseignant(String keyword);

    EnseignantDTO getEnseignantByUsername(String username);

    List<EnseignantDTO> getEnseignantDoctor();
}
