package com.proyecto.entrevistas.repository;

import com.proyecto.entrevistas.model.AgendaPrueba;
import com.proyecto.entrevistas.model.ViewModelAgendaCandidato;

import java.time.Instant;
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

    @Query("SELECT new com.proyecto.entrevistas.model.ViewModelAgendaCandidato(a.id, FORMAT(a.fecha,'yyyy-MM-dd'), a.estado, p.nombre as nombrePrueba) FROM AgendaPrueba a join Prueba p on a.idPrueba = p.id WHERE a.idCandidato = :idCandidato")
    List<ViewModelAgendaCandidato> listarAgendaPruebaCandidato(@Param("idCandidato") Long idCandidato);

}