package com.emploi.emploiapplication.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
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
public class Enseignant extends UserEntity {
    private String specialite;
    private String type;
    @OneToMany(mappedBy = "enseignant",fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection <Disponibilite> disponibilites = new ArrayList<>();
    @OneToMany(mappedBy = "enseignant",fetch = FetchType.LAZY)
    private List<Module> modules = new ArrayList<>();
}
