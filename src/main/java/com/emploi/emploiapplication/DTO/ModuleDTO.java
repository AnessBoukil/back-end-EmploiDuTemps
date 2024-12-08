package com.emploi.emploiapplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuleDTO {
    private Long idModule;
    private String libelleModule;
    private int volumeHorraire;
}
