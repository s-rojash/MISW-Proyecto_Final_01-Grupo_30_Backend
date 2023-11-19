package com.proyecto.proyectos.repository;

import com.proyecto.proyectos.model.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
    Proyecto findOneByNombre(String nombre);

    List<Proyecto> findAllByIdEmpresa(Long idEmpresa);

    @Query("SELECT p FROM Proyecto p WHERE p.idEmpresa = :idEmpresa and p.id = :id")
    Proyecto findOneById(Long idEmpresa, Long id);
}
