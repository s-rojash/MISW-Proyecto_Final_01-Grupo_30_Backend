package com.proyectofinalg30.candidatos.controller;

import com.proyectofinalg30.candidatos.model.Habilidad;
import com.proyectofinalg30.candidatos.service.HabilidadService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/habilidades")
public class HabilidadController {

    @Autowired
    private HabilidadService habilidadService;

    @PostMapping("/")
    public ResponseEntity<Habilidad> post(@Valid @RequestBody Habilidad habilidad) {
        this.habilidadService.save(habilidad);
        return new ResponseEntity<>(habilidad, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public List<Habilidad> getAll() {
        return habilidadService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habilidad> get(@PathVariable Long id) {
        Habilidad habilidad = habilidadService.list(id);
        return new ResponseEntity<>(habilidad, HttpStatus.OK);
    }

    @GetMapping("/tipo-habilidad/{nombre}")
    public List<Habilidad> getTipoHabilidad(@PathVariable String nombre) {
        return habilidadService.searchTipoHabilidad(nombre);
    }

    @GetMapping("/habilidad/{nombre}")
    public ResponseEntity<Habilidad> get(@PathVariable String nombre) {
        Habilidad habilidad = habilidadService.searchHabilidad(nombre);
        return new ResponseEntity<>(habilidad, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Habilidad> delete(@PathVariable Long id) {
        Habilidad habilidad = habilidadService.list(id);
        habilidadService.delete(habilidad);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("pong", HttpStatus.OK);
    }
}
