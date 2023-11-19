package com.proyecto.entrevistas.service;

import com.proyecto.entrevistas.model.ResultadoPrueba;
import com.proyecto.entrevistas.repository.ResultadoPruebaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class ResultadoPruebaService {

    @Autowired
    private ResultadoPruebaRepository resultadoPruebaRepository;

    public ResultadoPrueba save(ResultadoPrueba resultadoPrueba) {
        resultadoPrueba.setFecha(new Date(System.currentTimeMillis())); /// La fecha se establece de manera automática
        return resultadoPruebaRepository.save(resultadoPrueba);
    }

    // public List<ResultadoPrueba> listAll(Long idAgendaPrueba) {
    //     return resultadoPruebaRepository.findResultadoPruebaByIdAgendaPrueba(idAgendaPrueba);
    // }

    public ResultadoPrueba list(Long idAgendaPrueba) { 
        return resultadoPruebaRepository.findResultadoPruebaByIdAgendaPrueba(idAgendaPrueba); 
    }

}