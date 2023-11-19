package com.proyecto.entrevistas.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proyecto.bancopreguntas.model.Prueba;

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
@Table(name = "agendarPrueba")
public class AgendarPrueba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idCandidato;

    @ManyToOne
    @JoinColumn(name = "idPrueba")
    private Prueba prueba;

    public void setPrueba(Prueba prueba) {
        this.prueba = prueba;
    }

    @Column(name = "fecha")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date fecha;

    private Integer puntos;

    @NotEmpty(message = "El campo de estado no puede estar vacio")
    private String estado;

}
