package com.proyecto.entrevistas.repository;

import com.proyecto.entrevistas.model.AgendaPrueba;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaPruebaRepository extends JpaRepository<AgendaPrueba, Long> {

    @Query("SELECT a FROM AgendaPrueba a WHERE a.idEmpresa = :idEmpresa")
    List<AgendaPrueba> findAgendaPruebaByIdEmpresa(@Param("idEmpresa") Long idEmpresa);

    @Query("SELECT a FROM AgendaPrueba a WHERE a.idEmpresa = :idEmpresa and a.id = :id ")
    AgendaPrueba findAgendaPruebaByIdAndIdEmpresa(@Param("idEmpresa") Long idEmpresa, @Param("id") Long id);
}