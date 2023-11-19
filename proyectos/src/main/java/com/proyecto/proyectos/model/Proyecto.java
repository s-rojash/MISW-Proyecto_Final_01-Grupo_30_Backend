package com.proyecto.proyectos.model;

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
@Table(name = "proyecto", uniqueConstraints = { @UniqueConstraint(columnNames = { "nombre" }) })
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idEmpresa;

    @NotEmpty(message = "El campo de nombre de proyecto no puede estar vacio")
    private String nombre;

    @NotEmpty(message = "El campo de descripción no puede estar vacio")
    private String descripcion;


}
