package com.proyecto.bancopreguntas.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pruebaCandidato")
public class PruebaCandidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idCandidato;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPrueba")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private Prueba prueba;

    private Short puntaje;

    @NotEmpty(message = "El campo de estado no puede estar vacio")
    private String estado;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String fechaPresentacion;
}
