package com.proyecto.proyectos.repository;

import com.proyecto.proyectos.model.AsignarEquipo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AsignarEquipoRepository extends JpaRepository<AsignarEquipo, Long> {

    @Query("SELECT a FROM AsignarEquipo a JOIN a.equipo e JOIN e.proyecto p WHERE p.idEmpresa = :idEmpresa")
    List<AsignarEquipo> findEquipoAndProyectoByIdEmpresa(@Param("idEmpresa") Long idEmpresa);

    @Query("SELECT a FROM AsignarEquipo a JOIN a.equipo e JOIN e.proyecto p WHERE p.idEmpresa = :idEmpresa and a.id = :id")
    AsignarEquipo findEquipoByIdAndIdEmpresa(@Param("idEmpresa") Long idEmpresa, @Param("id") Long id);
}
