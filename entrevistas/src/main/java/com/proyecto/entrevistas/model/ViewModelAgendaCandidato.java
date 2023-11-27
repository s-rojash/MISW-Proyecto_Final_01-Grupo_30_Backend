package com.proyecto.entrevistas.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

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

public class ViewModelAgendaCandidato {

    @Id
    private Long id;
	private String fecha;
    private String estado;
    private String nombrePrueba;

    public ViewModelAgendaCandidato(
        long id,
        String fecha,
        String estado,
        String nombrePrueba
    ){
        this.id = id;
        this.fecha = fecha;
        this.estado = estado;
        this.nombrePrueba = nombrePrueba;
    }
}