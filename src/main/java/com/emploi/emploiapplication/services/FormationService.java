package com.emploi.emploiapplication.services;

import com.emploi.emploiapplication.entities.Formation;

import java.util.List;

public interface FormationService {
    Formation addFormation(Formation formation);

    List<Formation> getAllFormations();

    Formation getFomationById(Long id);

    List<Formation> chercherFormation(String keyword);

    Formation updateFormation(Long id, Formation formation);

    String deleteFormation(Long id);
}
