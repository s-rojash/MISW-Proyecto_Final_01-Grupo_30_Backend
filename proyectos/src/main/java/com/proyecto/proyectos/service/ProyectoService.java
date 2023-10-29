package com.proyecto.proyectos.service;

import com.proyecto.proyectos.model.Proyecto;
import com.proyecto.proyectos.repository.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;

    public Proyecto searchNombre(String nombre) {
        return proyectoRepository.findOneByNombre(nombre);
    }

    public Proyecto save(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    public Proyecto list(Long idEmpresa, Long id) { return proyectoRepository.findOneById(idEmpresa, id); }

    public List<Proyecto> listEmpresa(Long idEmpresa) { return proyectoRepository.findAllByIdEmpresa(idEmpresa); }
    public void delete(Proyecto proyecto) {
        proyectoRepository.delete(proyecto);
    }
}
