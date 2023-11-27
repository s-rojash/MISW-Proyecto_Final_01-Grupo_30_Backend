package com.proyectofinalg30.candidatos.service;

import com.proyectofinalg30.candidatos.model.Habilidad;
import com.proyectofinalg30.candidatos.repository.HabilidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabilidadService {

    @Autowired
    private HabilidadRepository habilidadRepository;

    public List<Habilidad> searchTipoHabilidad(String tipoHabilidad) {
        return habilidadRepository.findOneByTipoHabilidad(tipoHabilidad);
    }

    public Habilidad searchHabilidad(String habilidad) {
        return habilidadRepository.findOneByHabilidad(habilidad);
    }

    public Habilidad save(Habilidad habilidad) {
        return habilidadRepository.save(habilidad);
    }

    public List<Habilidad> listAll() {
        return habilidadRepository.findAll();
    }

    public Habilidad list(Long id) { return habilidadRepository.findOneById(id); }

    public void delete(Habilidad habilidad) {
        habilidadRepository.delete(habilidad);
    }
}
