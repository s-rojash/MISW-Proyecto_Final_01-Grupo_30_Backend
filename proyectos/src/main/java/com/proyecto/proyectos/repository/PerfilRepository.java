package com.proyecto.proyectos.repository;

import com.proyecto.proyectos.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    Perfil findOneById(Long id);
}
