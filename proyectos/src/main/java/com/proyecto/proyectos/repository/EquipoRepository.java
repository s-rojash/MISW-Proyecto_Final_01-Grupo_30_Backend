package com.proyecto.proyectos.repository;

import com.proyecto.proyectos.model.Equipo;
import com.proyecto.proyectos.model.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long> {

    @Query("SELECT e FROM Equipo e JOIN e.proyecto p WHERE p.idEmpresa = :idEmpresa")
    List<Equipo> findEquipoAndProyectoByIdEmpresa(@Param("idEmpresa") Long idEmpresa);

    @Query("SELECT e FROM Equipo e JOIN e.proyecto p WHERE p.idEmpresa = :idEmpresa and e.id = :id")
    Equipo findEquipoByIdAndIdEmpresa(@Param("idEmpresa") Long idEmpresa, @Param("id") Long id);

    @Query("SELECT e FROM Equipo e JOIN e.proyecto p WHERE p.idEmpresa = :idEmpresa and p.id = :id")
    List<Equipo> findEquipoAndIdEmpresaAndId(@Param("idEmpresa") Long idEmpresa, @Param("id") Long id);

    @Query("SELECT e FROM Equipo e JOIN e.proyecto p JOIN e.perfil pe WHERE p.idEmpresa = :idEmpresa and pe.id = :id")
    List<Equipo> findEquipoAndIdEmpresaAndIdPerfil(@Param("idEmpresa") Long idEmpresa, @Param("id") Long id);
}
