package com.proyecto.bancopreguntas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Pregunta")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El campo de pregunta no puede estar vacio")
    private String respuesta;

    @NotEmpty(message = "El campo de estado no puede estar vacio")
    private String estado;

    private Integer puntos;

    @ManyToOne
    @JoinColumn(name = "idPregunta")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Pregunta pregunta;

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }
}
