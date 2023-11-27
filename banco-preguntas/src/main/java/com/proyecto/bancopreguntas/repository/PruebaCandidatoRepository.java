package com.proyecto.bancopreguntas.repository;

import com.proyecto.bancopreguntas.model.PruebaCandidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PruebaCandidatoRepository extends JpaRepository<PruebaCandidato, Long> {

    @Query("SELECT pc FROM PruebaCandidato pc JOIN pc.prueba p JOIN p.bancoPreguntas b WHERE b.idEmpresa = :idEmpresa")
    List<PruebaCandidato> findPruebaByIdEmpresa(@Param("idEmpresa") Long idEmpresa);

    @Query("SELECT pc FROM PruebaCandidato pc WHERE pc.id = :id")
    PruebaCandidato findPruebaById(@Param("id") Long id);

    @Query("SELECT pc FROM PruebaCandidato pc WHERE pc.idCandidato = :id")
    List<PruebaCandidato> findPruebaByIdAndIdCandidato(@Param("id") Long id);

    @Query("SELECT pc FROM PruebaCandidato pc JOIN pc.prueba p WHERE p.id = :id")
    List<PruebaCandidato> findPruebaByIdAndIdPrueba(@Param("id") Long id);

    @Query("SELECT pc FROM PruebaCandidato pc JOIN pc.prueba p JOIN p.bancoPreguntas b WHERE b.idEmpresa = :idEmpresa AND pc.estado in (:estados)")
    List<PruebaCandidato> findPruebaByIdAndEstado(@Param("idEmpresa") Long idEmpresa, @Param("estados") List<String> estados);

    @Query("SELECT pc FROM PruebaCandidato pc JOIN pc.prueba p JOIN p.bancoPreguntas b WHERE b.idEmpresa = :idEmpresa AND pc.fechaPresentacion BETWEEN :fechaIncio AND :fechaFin")
    List<PruebaCandidato> findPruebaByIdAndFechas(@Param("idEmpresa") Long idEmpresa, @Param("fechaIncio") String fechaIncio, @Param("fechaFin") String fechaFin);
}
