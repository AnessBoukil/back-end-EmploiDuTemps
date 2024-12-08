package com.emploi.emploiapplication.settings;

import com.emploi.emploiapplication.DTO.EnseignantDTO;
import com.emploi.emploiapplication.entities.Enseignant;

public interface MailSenderService {
    void sendAccountToProf(EnseignantDTO enseignantDTO);
}
