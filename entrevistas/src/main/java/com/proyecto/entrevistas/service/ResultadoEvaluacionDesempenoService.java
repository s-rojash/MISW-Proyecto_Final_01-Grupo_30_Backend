package com.proyecto.entrevistas.service;

import com.proyecto.entrevistas.model.ResultadoEvaluacionDesempeno;
import com.proyecto.entrevistas.model.ResultadoPrueba;
import com.proyecto.entrevistas.repository.ResultadoEvaluacionDesempenoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class ResultadoEvaluacionDesempenoService {

    @Autowired
    private ResultadoEvaluacionDesempenoRepository resultadoEvaluacionDesempenoRepository;

    public ResultadoEvaluacionDesempeno save(ResultadoEvaluacionDesempeno resultadoEvaluacionDesempeno) {
        resultadoEvaluacionDesempeno.setFecha(new Date(System.currentTimeMillis())); /// La fecha se establece de manera automática
        return resultadoEvaluacionDesempenoRepository.save(resultadoEvaluacionDesempeno);
    }

    public ResultadoPrueba list(Long idAsignarEquipo) { 
        return resultadoEvaluacionDesempenoRepository.findResultadoPruebaByidAsignarEquipo(idAsignarEquipo); 
    }

}