package com.proyecto.entrevistas.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

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
@Table(name = "resultado_evaluacion_desempeno")
public class ResultadoEvaluacionDesempeno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idAsignarEquipo;

    private Long idPrueba;

    @Column(name = "fecha")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private java.util.Date fecha;

    private Integer puntos;

    public ResultadoEvaluacionDesempeno(
        long id,
        long idAsignarEquipo,
        long idPrueba,
        Date fecha,
        Integer puntos
    ){
        this.id = id;
        this.idAsignarEquipo = idAsignarEquipo;
        this.idPrueba = idPrueba;
        this.fecha = fecha;
        this.puntos = puntos;
    }
}