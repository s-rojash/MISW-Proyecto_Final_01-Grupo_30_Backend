package com.proyecto.bancopreguntas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Pregunta")
public class Pregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El campo de pregunta no puede estar vacio")
    private String pregunta;

    @ManyToOne
    @JoinColumn(name = "idBancoPreguntas")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private BancoPreguntas bancoPreguntas;

    public void setBancoPreguntas(BancoPreguntas bancoPreguntas) {
        this.bancoPreguntas = bancoPreguntas;
    }

    @OneToMany(mappedBy = "pregunta", cascade = CascadeType.ALL)
    private Set<Respuesta> respuestas = new HashSet<>();
}
