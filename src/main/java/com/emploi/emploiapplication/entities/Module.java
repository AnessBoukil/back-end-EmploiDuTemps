package com.emploi.emploiapplication.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idModule;
    private String libelleModule;
    private int volumeHorraire;
    @ManyToOne
    private Enseignant enseignant;
    @ManyToMany
    @JoinTable(
            name = "Module_Partie",
            joinColumns = @JoinColumn(name = "idPartie"),
            inverseJoinColumns = @JoinColumn(name = "idModule"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Partie> partie=new ArrayList<>();
    @OneToMany(mappedBy = "module",fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Seance> seances= new ArrayList<>();
}
