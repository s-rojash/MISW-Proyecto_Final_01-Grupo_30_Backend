package com.proyectofinalg30.candidatos.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "candidatoHabilidad")
public class CandidatoHabilidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCandidato")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private Candidato candidato;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idHabilidad")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private Habilidad habilidad;

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public void setHabilidad(Habilidad habilidad) {
        this.habilidad = habilidad;
    }

}
