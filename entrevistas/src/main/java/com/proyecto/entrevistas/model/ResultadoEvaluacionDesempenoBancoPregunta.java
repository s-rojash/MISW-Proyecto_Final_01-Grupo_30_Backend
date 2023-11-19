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
@Table(name = "resultado_evaluacion_desempeno_banco_pregunta")
public class ResultadoEvaluacionDesempenoBancoPregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idResultadoEvaluacionDesempeno;
    private Long idBancoPreguntas;
    private Long idPregunta;
    private Long idRespuesta;
    private int puntaje;
}