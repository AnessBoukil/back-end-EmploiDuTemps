package com.emploi.emploiapplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminDTO {
    private Long idUser;
    private String nom;
    private String prenom;
    private String nTele;
    private String email;
    private UserAccountDTO userAccountDTO;
}
