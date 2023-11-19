package com.proyecto.bancopreguntas.repository;

import com.proyecto.bancopreguntas.model.AgendarPrueba;
import com.proyecto.bancopreguntas.model.Prueba;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendarPruebaRepository extends JpaRepository<AgendarPrueba, Long> {

    @Query("SELECT a FROM AgendarPrueba a JOIN a.prueba p JOIN p.bancoPreguntas b WHERE b.idEmpresa = :idEmpresa")
    List<AgendarPrueba> findAgendarPruebaByIdEmpresa(@Param("idEmpresa") Long idEmpresa);

    @Query("SELECT a FROM AgendarPrueba a JOIN a.prueba p JOIN p.bancoPreguntas b WHERE b.idEmpresa = :idEmpresa and a.id = :id ")
    AgendarPrueba findAgendarPruebaByIdAndIdEmpresa(@Param("idEmpresa") Long idEmpresa, @Param("id") Long id);
}
