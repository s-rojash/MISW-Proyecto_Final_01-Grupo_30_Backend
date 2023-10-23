package com.proyecto.bancopreguntas.repository;

import com.proyecto.bancopreguntas.model.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {

    @Query("SELECT r FROM Respuesta r JOIN r.pregunta p JOIN p.bancoPreguntas bp WHERE bp.idEmpresa = :idEmpresa and bp.id = :idBanco and p.id = :idPregunta ")
    List<Respuesta> findRespuestasByIdEmpresaAndBancoPreguntas(@Param("idEmpresa") Long idEmpresa, @Param("idBanco") Long idBanco, @Param("idPregunta") Long idPregunta);

    @Query("SELECT r FROM Respuesta r JOIN r.pregunta p JOIN p.bancoPreguntas bp WHERE bp.idEmpresa = :idEmpresa and bp.id = :idBanco and p.id = :idPregunta and r.id = :id ")
    Respuesta findRespuestasByIdAndIdEmpresa(@Param("idEmpresa") Long idEmpresa, @Param("idBanco") Long idBanco, @Param("idPregunta") Long idPregunta, @Param("id") Long id);

}
