package com.proyecto.proyectos.service;

import com.proyecto.proyectos.model.Equipo;
import com.proyecto.proyectos.repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    public Equipo save(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    public List<Equipo> listAll(Long idEmpresa) {
        return equipoRepository.findEquipoAndProyectoByIdEmpresa(idEmpresa);
    }

    public Equipo list(Long idEmpresa, Long id) { return equipoRepository.findEquipoByIdAndIdEmpresa(idEmpresa, id); }

    public List<Equipo> listAllProyectos(Long idEmpresa, Long id) {
        return equipoRepository.findEquipoAndIdEmpresaAndId(idEmpresa, id);
    }

    public List<Equipo> listAllPerfiles(Long idEmpresa, Long id) {
        return equipoRepository.findEquipoAndIdEmpresaAndIdPerfil(idEmpresa, id);
    }
    public void delete(Equipo equipo) {
        equipoRepository.delete(equipo);
    }
}
