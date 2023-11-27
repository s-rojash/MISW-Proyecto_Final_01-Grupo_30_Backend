package com.proyectofinalg30.candidatos.repository;

import com.proyectofinalg30.candidatos.model.Habilidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabilidadRepository extends JpaRepository<Habilidad, Long> {
    List<Habilidad> findOneByTipoHabilidad(String tipoHabilidad);

    Habilidad findOneByHabilidad(String habilidad);

    Habilidad findOneById(Long id);
}
