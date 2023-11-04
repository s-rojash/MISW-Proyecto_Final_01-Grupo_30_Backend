package com.proyecto.bancopreguntas.controller;

import com.proyecto.bancopreguntas.model.Prueba;
import com.proyecto.bancopreguntas.service.PruebaService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pruebas")
public class PruebaController {

    @Autowired
    private PruebaService pruebaService;

    @PostMapping("/")
    public ResponseEntity<Prueba> post(@Valid @RequestBody Prueba prueba) {
        this.pruebaService.save(prueba);
        return new ResponseEntity<>(prueba, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public List<Prueba> getAll() {
        return pruebaService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prueba> getid(@PathVariable Long id) {
        Prueba prueba = pruebaService.list(id);
        return new ResponseEntity<>(prueba, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Prueba> delete(@PathVariable Long id) {
        Prueba prueba = pruebaService.list(id);
        pruebaService.delete(prueba);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("pong", HttpStatus.OK);
    }
}
