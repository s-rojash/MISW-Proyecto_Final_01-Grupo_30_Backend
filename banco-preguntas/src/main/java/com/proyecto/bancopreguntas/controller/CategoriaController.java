package com.proyecto.bancopreguntas.controller;

import com.proyecto.bancopreguntas.model.Categoria;
import com.proyecto.bancopreguntas.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping("/")
    public ResponseEntity<Categoria> post(@Valid @RequestBody Categoria categoria) {
        this.categoriaService.save(categoria);
        return new ResponseEntity<>(categoria, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public List<Categoria> getAll() {
        return categoriaService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getid(@PathVariable Long id) {
        Categoria categoria = categoriaService.list(id);
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Categoria> delete(@PathVariable Long id) {
        Categoria categoria = categoriaService.list(id);
        categoriaService.delete(categoria);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("pong", HttpStatus.OK);
    }
}
