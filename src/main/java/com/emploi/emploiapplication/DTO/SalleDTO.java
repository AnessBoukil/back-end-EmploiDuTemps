package com.emploi.emploiapplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalleDTO {
    private Long idSalle;
    private String libelleSalle;
    private String bloc;
    private String typeSalle;
}
