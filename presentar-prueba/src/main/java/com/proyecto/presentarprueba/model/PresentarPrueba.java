package com.proyecto.presentarprueba.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String fecha;
}
