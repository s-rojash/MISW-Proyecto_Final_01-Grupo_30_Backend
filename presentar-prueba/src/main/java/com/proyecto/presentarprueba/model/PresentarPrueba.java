package com.proyecto.presentarprueba.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "presentarPrueba")
public class PresentarPrueba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idCandidato;

    private Long idPrueba;

    private Long idPregunta;

    private Long idRespuesta;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String fecha;

}
