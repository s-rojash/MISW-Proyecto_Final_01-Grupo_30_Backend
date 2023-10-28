package com.proyecto.proyectos.service;

import com.proyecto.proyectos.model.Perfil;
import com.proyecto.proyectos.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    public Perfil save(Perfil perfil) {
        return perfilRepository.save(perfil);
    }

    public List<Perfil> listAll() {
        return perfilRepository.findAll();
    }

    public Perfil list(Long id) { return perfilRepository.findOneById(id); }

    public void delete(Perfil perfil) {
        perfilRepository.delete(perfil);
    }
}
