package com.proyecto.bancopreguntas.service;

import com.proyecto.bancopreguntas.model.AgendarPrueba;
import com.proyecto.bancopreguntas.repository.AgendarPruebaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendarPruebaService {

    @Autowired
    private AgendarPruebaRepository agendarPruebaRepository;

    public AgendarPrueba save(AgendarPrueba agendarPrueba) {
        return agendarPruebaRepository.save(agendarPrueba);
    }

    public List<AgendarPrueba> listAll(Long idEmpresa) {
        return agendarPruebaRepository.findAgendarPruebaByIdEmpresa(idEmpresa);
    }

    public AgendarPrueba list(Long idEmpresa, Long id) { return agendarPruebaRepository.findAgendarPruebaByIdAndIdEmpresa(idEmpresa, id); }

    public void delete(AgendarPrueba agendarPrueba) {
        agendarPruebaRepository.delete(agendarPrueba);
    }
}
