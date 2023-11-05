package com.proyecto.bancopreguntas.repository;

import com.proyecto.bancopreguntas.model.Prueba;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PruebaRepository extends JpaRepository<Prueba, Long> {

    @Query("SELECT p FROM Prueba p JOIN p.bancoPreguntas b WHERE b.idEmpresa = :idEmpresa")
    List<Prueba> findPruebaByIdEmpresa(@Param("idEmpresa") Long idEmpresa);

    @Query("SELECT p FROM Prueba p JOIN p.bancoPreguntas b WHERE b.idEmpresa = :idEmpresa and p.id = :id ")
    Prueba findPruebaByIdAndIdEmpresa(@Param("idEmpresa") Long idEmpresa, @Param("id") Long id);

}
