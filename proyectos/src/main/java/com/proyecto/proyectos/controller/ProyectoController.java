package com.proyecto.proyectos.controller;

import com.proyecto.proyectos.model.Proyecto;
import com.proyecto.proyectos.service.ProyectoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/proyectos")
public class ProyectoController {

    @Autowired
    private ProyectoService proyectoService;

    @PostMapping("/")
    public ResponseEntity<Proyecto> post(@Valid @RequestBody Proyecto proyecto) {
        this.proyectoService.save(proyecto);
        return new ResponseEntity<>(proyecto, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public List<Proyecto> getAll() {
        return proyectoService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proyecto> getid(@PathVariable Long id) {
        Proyecto proyecto = proyectoService.list(id);
        return new ResponseEntity<>(proyecto, HttpStatus.OK);
    }

    @GetMapping("/empresa/{id}")
    public List<Proyecto> getIdEmpresas(@PathVariable Long id) {
        return proyectoService.listEmpresa(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Proyecto> delete(@PathVariable Long id) {
        Proyecto proyecto = proyectoService.list(id);
        proyectoService.delete(proyecto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("pong", HttpStatus.OK);
    }
}
