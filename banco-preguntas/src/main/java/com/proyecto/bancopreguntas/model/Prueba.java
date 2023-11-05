package com.proyecto.bancopreguntas.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "prueba")
public class Prueba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El campo de nombre no puede estar vacio")
    private String nombre;

    @NotEmpty(message = "El campo de descripción no puede estar vacio")
    private String descripcion;

    @ManyToMany
    @JsonBackReference
    @JoinTable(name = "PruebaBancos", 
    joinColumns = @JoinColumn(name = "idPrueba", referencedColumnName = "id"), 
    inverseJoinColumns = @JoinColumn(name = "idBancoPreguntas", referencedColumnName = "id"))
    private Set<BancoPreguntas> bancoPreguntas;

    public Set<BancoPreguntas> getBancosPreguntas() {
      return bancoPreguntas;
    }

	public void setBancosPreguntas(Set<BancoPreguntas> bancoPreguntas) {
		this.bancoPreguntas = bancoPreguntas;
	}
}
