package com.proyecto.bancopreguntas.controller;

import com.proyecto.bancopreguntas.model.BancoPreguntas;
import com.proyecto.bancopreguntas.model.Categoria;
import com.proyecto.bancopreguntas.repository.CategoriaRepository;
import com.proyecto.bancopreguntas.service.BancoPreguntasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/banco-preguntas")
public class BancoPreguntasController {

    @Autowired
    private BancoPreguntasService bancoPreguntasService;

    @Autowired(required=false)
    private CategoriaRepository categoriaRepository;

    @PostMapping("/")
    public ResponseEntity<BancoPreguntas> post(@Valid @RequestBody BancoPreguntas bancoPreguntas) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(bancoPreguntas.getCategoria().getId());
        bancoPreguntas.setCategoria(categoriaOptional.get());
        this.bancoPreguntasService.save(bancoPreguntas);
        return new ResponseEntity<>(bancoPreguntas, HttpStatus.CREATED);
    }

    @GetMapping("/empresa/{idEmpresa}")
    public List<BancoPreguntas> getAll(@PathVariable Long idEmpresa) {
        return bancoPreguntasService.listAll(idEmpresa);
    }

    @GetMapping("/{id}/empresa/{idEmpresa}")
    public ResponseEntity<BancoPreguntas> getId(@PathVariable Long idEmpresa, @PathVariable Long id) {
        BancoPreguntas bancoPreguntas = bancoPreguntasService.list(idEmpresa, id);
        return new ResponseEntity<>(bancoPreguntas, HttpStatus.OK);
    }

    @GetMapping("/empresa/{idEmpresa}/tipo-banco/{tipoBanco}")
    public List<BancoPreguntas> getAllProyecto(@PathVariable Long idEmpresa, @PathVariable String tipoBanco) {
        return bancoPreguntasService.listAllTipobanco(idEmpresa, tipoBanco);
    }

    @GetMapping("/empresa/{idEmpresa}/categoria/{id}")
    public List<BancoPreguntas> getAllPerfil(@PathVariable Long idEmpresa, @PathVariable Long id) {
        return bancoPreguntasService.listAllCategorias(idEmpresa, id);
    }

    @DeleteMapping("/{id}/empresa/{idEmpresa}")
    public ResponseEntity<BancoPreguntas> delete(@PathVariable Long idEmpresa, Long id) {
        BancoPreguntas bancoPreguntas = bancoPreguntasService.list(idEmpresa, id);
        bancoPreguntasService.delete(bancoPreguntas);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("pong", HttpStatus.OK);
    }
}
