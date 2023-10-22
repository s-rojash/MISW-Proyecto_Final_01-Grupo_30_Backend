package com.proyecto.proyectos.controller;

import com.proyecto.proyectos.model.Perfil;
import com.proyecto.proyectos.service.PerfilService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perfiles")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @PostMapping("/")
    public ResponseEntity<Perfil> post(@Valid @RequestBody Perfil perfil) {
        this.perfilService.save(perfil);
        return new ResponseEntity<>(perfil, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public List<Perfil> getAll() {
        return perfilService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Perfil> getid(@PathVariable Long id) {
        Perfil perfil = perfilService.list(id);
        return new ResponseEntity<>(perfil, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Perfil> delete(@PathVariable Long id) {
        Perfil perfil = perfilService.list(id);
        perfilService.delete(perfil);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("pong", HttpStatus.OK);
    }
}
