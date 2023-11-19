package com.proyecto.proyectos.controller;

import com.proyecto.proyectos.model.Equipo;
import com.proyecto.proyectos.model.Perfil;
import com.proyecto.proyectos.model.Proyecto;
import com.proyecto.proyectos.repository.PerfilRepository;
import com.proyecto.proyectos.repository.ProyectoRepository;
import com.proyecto.proyectos.security.TokenUtils;
import com.proyecto.proyectos.service.EquipoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin()
@RestController
@RequestMapping("/equipos")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    @Autowired(required=false)
    private ProyectoRepository proyectoRepository;

    @Autowired(required=false)
    private PerfilRepository perfilRepository;

    @Value("${variable.AccessTokenSecret}")
    private String miAccessTokenSecret;
    
    @PostMapping("/")
    public ResponseEntity<Equipo> post(@Valid @RequestBody Equipo equipo) {
        Optional<Proyecto> proyectoOptional = proyectoRepository.findById(equipo.getProyecto().getId());
        Optional<Perfil> perfilOptional = perfilRepository.findById(equipo.getPerfil().getId());
        equipo.setProyecto(proyectoOptional.get());
        equipo.setPerfil(perfilOptional.get());
        this.equipoService.save(equipo);
        return new ResponseEntity<>(equipo, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public List<Equipo> getAll(HttpServletRequest request) {
        String idEmpresa = TokenUtils.decryptToken(request, miAccessTokenSecret);
        return equipoService.listAll(Long.valueOf(idEmpresa));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipo> getId(HttpServletRequest request, @PathVariable Long id) {
        String idEmpresa = TokenUtils.decryptToken(request, miAccessTokenSecret);
        Equipo equipo = equipoService.list(Long.valueOf(idEmpresa), id);
        return new ResponseEntity<>(equipo, HttpStatus.OK);
    }

    @GetMapping("/proyecto/{id}")
    public List<Equipo> getAllProyecto(HttpServletRequest request, @PathVariable Long id) {
        String idEmpresa = TokenUtils.decryptToken(request, miAccessTokenSecret);
        return equipoService.listAllProyectos(Long.valueOf(idEmpresa), id);
    }

    @GetMapping("/perfil/{id}")
    public List<Equipo> getAllPerfil(HttpServletRequest request, @PathVariable Long id) {
        String idEmpresa = TokenUtils.decryptToken(request, miAccessTokenSecret);
        return equipoService.listAllPerfiles(Long.valueOf(idEmpresa), id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Equipo> delete(HttpServletRequest request, @PathVariable Long id) {
        String idEmpresa = TokenUtils.decryptToken(request, miAccessTokenSecret);
        Equipo equipo = equipoService.list(Long.valueOf(idEmpresa), id);
        equipoService.delete(equipo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("pong", HttpStatus.OK);
    }
}
