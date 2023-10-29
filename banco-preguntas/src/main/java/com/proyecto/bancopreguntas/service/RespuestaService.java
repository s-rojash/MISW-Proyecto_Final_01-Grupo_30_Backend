package com.proyecto.bancopreguntas.service;

import com.proyecto.bancopreguntas.model.Respuesta;
import com.proyecto.bancopreguntas.repository.RespuestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RespuestaService {

    @Autowired
    private RespuestaRepository respuestaRepository;

    public Respuesta save(Respuesta respuesta) {
        return respuestaRepository.save(respuesta);
    }

    public List<Respuesta> listAll(Long idEmpresa, Long idBanco, Long idPregunta) {
        return respuestaRepository.findRespuestasByIdEmpresaAndBancoPreguntas(idEmpresa, idBanco, idPregunta);
    }

    public Respuesta list(Long idEmpresa, Long idBanco, Long idPregunta, Long id) {
        return respuestaRepository.findRespuestasByIdAndIdEmpresa(idEmpresa, idBanco, idPregunta, id);
    }

    public void delete(Respuesta respuesta) {
        respuestaRepository.delete(respuesta);
    }
}
