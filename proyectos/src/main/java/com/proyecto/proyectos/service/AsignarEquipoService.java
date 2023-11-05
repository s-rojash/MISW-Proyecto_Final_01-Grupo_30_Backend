package com.proyecto.proyectos.service;

import com.proyecto.proyectos.model.AsignarEquipo;
import com.proyecto.proyectos.repository.AsignarEquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AsignarEquipoService {

    @Autowired
    private AsignarEquipoRepository asignarEquipoRepository;

    public AsignarEquipo save(AsignarEquipo asignarEquipo) {
        return asignarEquipoRepository.save(asignarEquipo);
    }

    public List<AsignarEquipo> listAll(Long idEmpresa) {
        return asignarEquipoRepository.findEquipoAndProyectoByIdEmpresa(idEmpresa);
    }

    public AsignarEquipo list(Long idEmpresa, Long id) { return asignarEquipoRepository.findEquipoByIdAndIdEmpresa(idEmpresa, id); }

    public void delete(AsignarEquipo asignarEquipo) {
        asignarEquipoRepository.delete(asignarEquipo);
    }
}
