package com.proyecto.bancopreguntas.service;

import com.proyecto.bancopreguntas.repository.PruebaRepository;
import com.proyecto.bancopreguntas.model.Prueba;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PruebaService {

    @Autowired
    private PruebaRepository pruebaRepository;

    public Prueba save(Prueba prueba) {
        return pruebaRepository.save(prueba);
    }

    public List<Prueba> listAll(Long idEmpresa) {
        return pruebaRepository.findPruebaByIdEmpresa(idEmpresa);
    }

    public Prueba list(Long idEmpresa, Long id) { return pruebaRepository.findPruebaByIdAndIdEmpresa(idEmpresa, id); }

    public void delete(Prueba prueba) {
        pruebaRepository.delete(prueba);
    }
}
