package com.proyectofinalg30.candidatos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "candidato", uniqueConstraints = { @UniqueConstraint(name = "emailUnique", columnNames = "email") })
public class Candidato {

    public Candidato(String nombres, String apellidos, String tipoDocumento, Double numDocumento, String celular, String email, String password) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tipoDocumento = tipoDocumento;
        this.numDocumento = numDocumento;
        this.celular = celular;
        this.email = email;
        this.password = password;
        this.createdAt = new Date();
    }

    public Candidato(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El campo de nombres no puede estar vacio")
    private String nombres;

    @NotEmpty(message = "El campo de apellidos no puede estar vacio")
    private String apellidos;

    @NotEmpty(message = "El campo de tipo documento no puede estar vacio")
    private String tipoDocumento;

    private Double numDocumento;

    @NotEmpty(message = "El campo de tipo documento no puede estar vacio")
    private String celular;

    @NotEmpty(message = "El campo de email no puede estar vacio")
    @Email(message = "Email no valido")
    private String email;

    @NotEmpty(message = "El campo de password no puede estar vacio")
    private String password;

    @Column(length = 500)
    private String token;

    private Date expireAt;

    private Date createdAt;
}
