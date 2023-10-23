package com.proyecto.bancopreguntas.repository;

import com.proyecto.bancopreguntas.model.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreguntaRepository extends JpaRepository<Pregunta, Long> {

    @Query("SELECT p FROM Pregunta p JOIN p.bancoPreguntas bp WHERE bp.idEmpresa = :idEmpresa and bp.id = :id ")
    List<Pregunta> findPreguntasByIdEmpresaAndBancoPreguntas(@Param("idEmpresa") Long idEmpresa, @Param("id") Long id);

    @Query("SELECT p FROM Pregunta p JOIN p.bancoPreguntas bp WHERE bp.idEmpresa = :idEmpresa and bp.id = :idBanco and p.id = :id ")
    Pregunta findPreguntasByIdAndIdEmpresa(@Param("idEmpresa") Long idEmpresa, @Param("idBanco") Long idBanco, @Param("id") Long id);

}
