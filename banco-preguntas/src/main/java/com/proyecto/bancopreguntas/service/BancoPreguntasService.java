package com.proyecto.bancopreguntas.service;

import com.proyecto.bancopreguntas.model.BancoPreguntas;
import com.proyecto.bancopreguntas.repository.BancoPreguntasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BancoPreguntasService {

    @Autowired
    private BancoPreguntasRepository bancoPreguntasRepository;

    public BancoPreguntas save(BancoPreguntas bancoPreguntas) {
        return bancoPreguntasRepository.save(bancoPreguntas);
    }

    public List<BancoPreguntas> listAll(Long idEmpresa) {
        return bancoPreguntasRepository.findBancoPreguntasByIdEmpresa(idEmpresa);
    }

    public BancoPreguntas list(Long idEmpresa, Long id) { return bancoPreguntasRepository.findBancoPreguntasByIdAndIdEmpresa(idEmpresa, id); }

    public List<BancoPreguntas> listAllCategorias(Long idEmpresa, Long id) {
        return bancoPreguntasRepository.findBancoPreguntasCategoriaByIdAndIdEmpresa(idEmpresa, id);
    }

    public List<BancoPreguntas> listAllTipobanco(Long idEmpresa, String tipobanco) {
        return bancoPreguntasRepository.findBancoPreguntasByIdAndIdEmpresaAndTipoBanco(idEmpresa, tipobanco);
    }
    public void delete(BancoPreguntas bancoPreguntas) {
        bancoPreguntasRepository.delete(bancoPreguntas);
    }
}
