package com.proyecto.proyectos.controller;

import com.proyecto.proyectos.model.Equipo;
import com.proyecto.proyectos.model.Perfil;
import com.proyecto.proyectos.model.Proyecto;
import com.proyecto.proyectos.repository.EquipoRepository;
import com.proyecto.proyectos.repository.PerfilRepository;
import com.proyecto.proyectos.repository.ProyectoRepository;
import com.proyecto.proyectos.service.EquipoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/equipos")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    @Autowired(required=false)
    private ProyectoRepository proyectoRepository;

    @Autowired(required=false)
    private PerfilRepository perfilRepository;
    @PostMapping("/")
    public ResponseEntity<Equipo> post(@Valid @RequestBody Equipo equipo) {
        Optional<Proyecto> proyectoOptional = proyectoRepository.findById(equipo.getProyecto().getId());
        Optional<Perfil> perfilOptional = perfilRepository.findById(equipo.getPerfil().getId());
        equipo.setProyecto(proyectoOptional.get());
        equipo.setPerfil(perfilOptional.get());
        this.equipoService.save(equipo);
        return new ResponseEntity<>(equipo, HttpStatus.CREATED);
    }

    @GetMapping("/empresa/{idEmpresa}")
    public List<Equipo> getAll(@PathVariable Long idEmpresa) {
        return equipoService .listAll(idEmpresa);
    }

    @GetMapping("/{id}/empresa/{idEmpresa}")
    public ResponseEntity<Equipo> getId(@PathVariable Long idEmpresa, @PathVariable Long id) {
        Equipo equipo = equipoService.list(idEmpresa, id);
        return new ResponseEntity<>(equipo, HttpStatus.OK);
    }

    @GetMapping("/empresa/{idEmpresa}/proyecto/{id}")
    public List<Equipo> getAllProyecto(@PathVariable Long idEmpresa, @PathVariable Long id) {
        return equipoService.listAllProyectos(idEmpresa, id);
    }

    @GetMapping("/empresa/{idEmpresa}/perfil/{id}")
    public List<Equipo> getAllPerfil(@PathVariable Long idEmpresa, @PathVariable Long id) {
        return equipoService.listAllPerfiles(idEmpresa, id);
    }

    @DeleteMapping("/{id}/empresa/{idEmpresa}")
    public ResponseEntity<Equipo> delete(@PathVariable Long idEmpresa, Long id) {
        Equipo equipo = equipoService.list(idEmpresa, id);
        equipoService.delete(equipo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("pong", HttpStatus.OK);
    }
}
