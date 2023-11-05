package com.proyecto.proyectos.model;

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
@Table(name = "asignarEquipo")
public class AsignarEquipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idEquipo")
    private Equipo equipo;

    private Long idCandidato;

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
}
