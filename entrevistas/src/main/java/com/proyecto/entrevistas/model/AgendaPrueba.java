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
@Entity
@Table(name = "agendaPrueba")
public class AgendaPrueba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idEmpresa;

    private Long idCandidato;

    private Long idPrueba;

    @Column(name = "fecha")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private java.util.Date fecha;

    private Integer puntos;

    @NotEmpty(message = "El campo de estado no puede estar vacio")
    private String estado;

    public AgendaPrueba(
        long id,
        long idEmpresa,
        long idCandidato,
        long idPrueba,
        Date fecha,
        Integer puntos,
        String estado    
    ){
        this.id = id;
        this.idEmpresa = idEmpresa;
        this.idCandidato = idCandidato;
        this.idPrueba = idPrueba;
        this.fecha = fecha;
        this.puntos = puntos;
        this.estado = estado;
    }
}