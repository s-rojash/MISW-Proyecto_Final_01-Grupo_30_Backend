package com.proyectofinalg30.candidatos.model;

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
@Table(name = "habilidad", uniqueConstraints = { @UniqueConstraint(name = "habilidadUnique", columnNames = "habilidad") })
public class Habilidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El campo de tipo de habilidad no puede estar vacio")
    private String tipoHabilidad;

    @NotEmpty(message = "El campo de habilidad no puede estar vacio")
    private String habilidad;

}
