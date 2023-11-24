package com.proyectofinalg30.candidatos.service;

import com.proyectofinalg30.candidatos.model.CandidatoHabilidad;
import com.proyectofinalg30.candidatos.repository.CandidatoHabilidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidatoHabilidadService {

    @Autowired
    private CandidatoHabilidadRepository candidatoHabilidadRepository;

    public List<CandidatoHabilidad> searchHabilidad(Long id) {
        return candidatoHabilidadRepository.findOneByHabilidad_Id(id);
    }

    public List<CandidatoHabilidad> searchCandidato(Long id) {
        return candidatoHabilidadRepository.findOneByCandidato_Id(id);
    }
    public CandidatoHabilidad save(CandidatoHabilidad candidatoHabilidad) {
        return candidatoHabilidadRepository.save(candidatoHabilidad);
    }

    public List<CandidatoHabilidad> listAll() {
        return candidatoHabilidadRepository.findAll();
    }

    public CandidatoHabilidad list(Long id) { return candidatoHabilidadRepository.findOneById(id); }

    public void delete(CandidatoHabilidad candidatoHabilidad) {
        candidatoHabilidadRepository.delete(candidatoHabilidad);
    }
}
