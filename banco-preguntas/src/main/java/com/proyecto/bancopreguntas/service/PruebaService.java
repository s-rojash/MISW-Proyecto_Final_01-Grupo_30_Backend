package com.proyecto.bancopreguntas.service;

import com.proyecto.bancopreguntas.model.Prueba;
import com.proyecto.bancopreguntas.repository.PruebaRepository;

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

    public List<Prueba> listAll() {
        return pruebaRepository.findAll();
    }

    public Prueba list(Long id) { return pruebaRepository.findOneById(id); }

    public void delete(Prueba prueba) {
        pruebaRepository.delete(prueba);
    }
}
