package com.proyecto.entrevistas.repository;

import com.proyecto.entrevistas.model.ResultadoPrueba;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultadoPruebaRepository extends JpaRepository<ResultadoPrueba, Long> {
    @Query("SELECT a FROM ResultadoPrueba a WHERE a.idAgendaPrueba = :idAgendaPrueba")
    ResultadoPrueba findResultadoPruebaByIdAgendaPrueba(@Param("idAgendaPrueba") Long idAgendaPrueba);

}