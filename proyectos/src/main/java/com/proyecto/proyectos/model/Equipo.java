package com.proyecto.proyectos.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "equipo")
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idProyecto")
    private Proyecto proyecto;

    @NotEmpty(message = "El campo de nombre del equipo no puede estar vacio")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "idPerfil")
    private Perfil perfil;

    private Integer cantidad;

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
}
