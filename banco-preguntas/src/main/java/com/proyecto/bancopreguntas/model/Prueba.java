package com.proyecto.bancopreguntas.model;

import java.util.HashSet;
import java.util.Set;

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
@Table(name = "Prueba")
public class Prueba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El campo de nombre no puede estar vacio")
    private String nombre;

    @NotEmpty(message = "El campo de descripción no puede estar vacio")
    private String despcripcion;

    @ManyToMany
    @JoinTable(
    name = "PruebaBancos", 
    joinColumns = @JoinColumn(name = "idPrueba"), 
    inverseJoinColumns = @JoinColumn(name = "idBancoPrueba"))
    Set<BancoPreguntas> PruebaBancos = new HashSet<>();

    public Set<BancoPreguntas> getBancos() {
		return PruebaBancos;
	}

	public void setBancos(Set<BancoPreguntas> PruebaBancos) {
		this.PruebaBancos = PruebaBancos;
	}
}
