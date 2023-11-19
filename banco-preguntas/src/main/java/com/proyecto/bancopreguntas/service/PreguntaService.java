package com.proyecto.bancopreguntas.service;

import com.proyecto.bancopreguntas.model.Pregunta;
import com.proyecto.bancopreguntas.repository.PreguntaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreguntaService {

    @Autowired
    private PreguntaRepository preguntaRepository;

    public Pregunta save(Pregunta pregunta) {
        return preguntaRepository.save(pregunta);
    }

    public List<Pregunta> listAll(Long idEmpresa, Long id) {
        return preguntaRepository.findPreguntasByIdEmpresaAndBancoPreguntas(idEmpresa, id);
    }

    public Pregunta list(Long idEmpresa, Long idBanco, Long id) { return preguntaRepository.findPreguntasByIdAndIdEmpresa(idEmpresa, idBanco, id); }

    public void delete(Pregunta pregunta) {
        preguntaRepository.delete(pregunta);
    }
}
