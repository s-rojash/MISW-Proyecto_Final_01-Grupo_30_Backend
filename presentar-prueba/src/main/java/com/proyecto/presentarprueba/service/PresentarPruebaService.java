package com.proyecto.presentarprueba.service;

import com.proyecto.presentarprueba.model.PresentarPrueba;
import com.proyecto.presentarprueba.repository.PresentarPruebaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PresentarPruebaService {

    @Autowired
    private PresentarPruebaRepository presentarPruebaRepository;

    public PresentarPrueba save(PresentarPrueba presentarPrueba) {
        return presentarPruebaRepository.save(presentarPrueba);
    }

    public List<PresentarPrueba> listAll() {
        return presentarPruebaRepository.findAll();
    }

    public PresentarPrueba listId(Long id, Long idCandidato) {
        return presentarPruebaRepository.findPresntarPruebaById(id, idCandidato);
    }

    public List<PresentarPrueba> listParametros(Long idPrueba, Long idCandidato) {
        return presentarPruebaRepository.findPresntarPruebaByIdCandidatoAndIdPrueba(idCandidato, idPrueba);
    }

    public void delete(PresentarPrueba presentarPrueba) {
        presentarPruebaRepository.delete(presentarPrueba);
    }
}
