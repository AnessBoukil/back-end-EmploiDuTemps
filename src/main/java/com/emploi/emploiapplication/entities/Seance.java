package com.emploi.emploiapplication.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdSeance;
    private String typeSeance;
    private String periode;
    private String jourSeance ;
    @ManyToOne
    private Salle salle;
    @ManyToOne
    private Enseignant enseignant;
    @ManyToOne
    private Module module;
    @ManyToOne
    private Section section;
    @ManyToOne
    private Groupe groupe;
}