package com.proyectofinalg30.candidatos.service;

import com.proyectofinalg30.candidatos.model.Candidato;
import com.proyectofinalg30.candidatos.repository.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidatoService {

    @Autowired
    private CandidatoRepository candidatoRepository;

    public Candidato searchEmail(String email) {
        return candidatoRepository.findOneByEmail(email);
    }

    public Candidato searchToken(String token) {
        return candidatoRepository.findOneByToken(token);
    }

    public Candidato save(Candidato candidato) {
        return candidatoRepository.save(candidato);
    }

    public List<Candidato> listAll() {
        return candidatoRepository.findAll();
    }

    public Candidato list(Long id) { return candidatoRepository.findOneById(id); }

    public void delete(Candidato candidato) {
        candidatoRepository.delete(candidato);
    }
}
