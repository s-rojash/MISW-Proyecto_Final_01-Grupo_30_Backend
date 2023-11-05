package com.proyecto.entrevistas.service;

import com.proyecto.entrevistas.model.AgendaPrueba;
import com.proyecto.entrevistas.repository.AgendaPruebaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaPruebaService {

    // @Autowired
    private AgendaPruebaRepository agendaPruebaRepository;

    public AgendaPrueba save(AgendaPrueba agendaPrueba) {
        return agendaPruebaRepository.save(agendaPrueba);
    }

    public List<AgendaPrueba> listAll(Long idEmpresa) {
        return agendaPruebaRepository.findAgendaPruebaByIdEmpresa(idEmpresa);
    }

    public AgendaPrueba list(Long idEmpresa, Long id) { return agendaPruebaRepository.findAgendaPruebaByIdAndIdEmpresa(idEmpresa, id); }

    public void delete(AgendaPrueba agendaPrueba) {
        agendaPruebaRepository.delete(agendaPrueba);
    }
}