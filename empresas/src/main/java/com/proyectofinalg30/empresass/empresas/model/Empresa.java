package com.proyectofinalg30.empresass.empresas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "empresa", uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }) })
public class Empresa {

    public Empresa(String razonSocial, String tipoDocumento, Double numDocumento, Byte digitoVerificacion, String email, String password, Date createdAt) {
        this.razonSocial = razonSocial;
        this.tipoDocumento = tipoDocumento;
        this.numDocumento = numDocumento;
        this.digitoVerificacion = digitoVerificacion;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
    }

    public Empresa(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El campo de nombres no puede estar vacio")
    private String razonSocial;

    @NotEmpty(message = "El campo de tipo documento no puede estar vacio")
    private String tipoDocumento;

    private Double numDocumento;

    private Byte digitoVerificacion;

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
