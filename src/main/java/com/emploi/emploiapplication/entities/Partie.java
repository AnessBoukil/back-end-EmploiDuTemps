package com.emploi.emploiapplication.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Partie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPartie;
    private String libellePartie;
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    @ManyToOne
    private Semestre semestre;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Module_Partie",
            joinColumns = @JoinColumn(name = "idPartie"),
            inverseJoinColumns = @JoinColumn(name = "idModule"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Module> modules = new ArrayList<>();
    @OneToMany(mappedBy = "partie",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List <Section> sections = new ArrayList<>();
}
