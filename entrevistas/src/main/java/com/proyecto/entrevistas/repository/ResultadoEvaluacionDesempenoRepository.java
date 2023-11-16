package com.proyecto.entrevistas.repository;

import com.proyecto.entrevistas.model.ResultadoEvaluacionDesempeno;
import com.proyecto.entrevistas.model.ResultadoPrueba;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultadoEvaluacionDesempenoRepository extends JpaRepository<ResultadoEvaluacionDesempeno, Long> {
    @Query("SELECT a FROM ResultadoEvaluacionDesempeno a WHERE a.idAsignarEquipo = :idAsignarEquipo")
    ResultadoPrueba findResultadoPruebaByidAsignarEquipo(@Param("idAsignarEquipo") Long idAsignarEquipo);
}