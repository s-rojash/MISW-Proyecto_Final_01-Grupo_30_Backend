package com.proyecto.bancopreguntas.service;

import com.proyecto.bancopreguntas.model.PruebaCandidato;
import com.proyecto.bancopreguntas.repository.PruebaCandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PruebaCandidatoService {

    @Autowired
    private PruebaCandidatoRepository pruebaCandidatoRepository;

    public PruebaCandidato save(PruebaCandidato pruebaCandidato) {
        return pruebaCandidatoRepository.save(pruebaCandidato);
    }

    public List<PruebaCandidato> listAll(Long idEmpresa) {
        return pruebaCandidatoRepository.findPruebaByIdEmpresa(idEmpresa);
    }

    public PruebaCandidato list(Long id) { return pruebaCandidatoRepository.findPruebaById(id); }

    public List<PruebaCandidato> listCandidato(Long id) { return pruebaCandidatoRepository.findPruebaByIdAndIdCandidato(id); }

    public List<PruebaCandidato> listPrueba(Long id) { return pruebaCandidatoRepository.findPruebaByIdAndIdPrueba(id); }

    public List<PruebaCandidato> listEstado(Long idEmpresa, List<String> estados) { return pruebaCandidatoRepository.findPruebaByIdAndEstado(idEmpresa, estados); }

    public List<PruebaCandidato> listFecha(Long idEmpresa, String fechaIncio, String fechaFin) { return pruebaCandidatoRepository.findPruebaByIdAndFechas(idEmpresa, fechaIncio, fechaFin); }
    public void delete(PruebaCandidato pruebaCandidato) {
        pruebaCandidatoRepository.delete(pruebaCandidato);
    }
}
