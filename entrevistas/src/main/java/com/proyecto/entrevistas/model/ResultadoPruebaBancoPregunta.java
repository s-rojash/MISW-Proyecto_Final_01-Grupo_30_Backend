package com.proyecto.entrevistas.model;

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
@Table(name = "resultado_prueba_banco_pregunta")
public class ResultadoPruebaBancoPregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idResultadoPrueba;
    private Long idBancoPreguntas;
    private Long idPregunta;
    private Long idRespuesta;
    private int puntaje;
}