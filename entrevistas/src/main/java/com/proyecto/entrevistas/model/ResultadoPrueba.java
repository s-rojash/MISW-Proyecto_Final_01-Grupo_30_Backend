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
@Entity
@Table(name = "resultado_prueba")
public class ResultadoPrueba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idAgendaPrueba;

    @Column(name = "fecha")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date fecha;

    public ResultadoPrueba(
        Long id, 
        Long idAgendaPrueba,
        java.util.Date fecha
        ){
            this.id = id;
            this.idAgendaPrueba = idAgendaPrueba;
            this.fecha = fecha;
        }
}