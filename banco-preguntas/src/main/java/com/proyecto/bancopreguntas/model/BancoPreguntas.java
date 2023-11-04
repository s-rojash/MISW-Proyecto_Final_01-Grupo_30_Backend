package com.proyecto.bancopreguntas.model;

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
@Table(name = "BancoPreguntas")
public class BancoPreguntas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idEmpresa;

    @NotEmpty(message = "El campo de tipo no puede estar vacio")
    private String tipoBanco;

    @ManyToOne
    @JoinColumn(name = "idCategoria")
    private Categoria categoria;

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @ManyToMany(mappedBy = "PruebaBancos")
    Set<Prueba> pruebas;
}
