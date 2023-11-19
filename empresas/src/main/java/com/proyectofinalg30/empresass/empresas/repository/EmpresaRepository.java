package com.proyectofinalg30.empresass.empresas.repository;

import com.proyectofinalg30.empresass.empresas.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    Empresa findOneByEmail(String email);

    Empresa findOneByToken(String token);

    Empresa findOneById(Long id);
}
