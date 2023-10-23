package com.proyecto.bancopreguntas.controller;

import com.proyecto.bancopreguntas.model.BancoPreguntas;
import com.proyecto.bancopreguntas.model.Pregunta;
import com.proyecto.bancopreguntas.repository.BancoPreguntasRepository;
import com.proyecto.bancopreguntas.service.PreguntaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/preguntas")
public class PreguntaController {

    @Autowired
    private PreguntaService preguntaService;

    @Autowired(required=false)
    private BancoPreguntasRepository bancoPreguntasRepository;

    @PostMapping("/")
    public ResponseEntity<Pregunta> post(@Valid @RequestBody Pregunta preguntas) {
        Optional<BancoPreguntas> bancoPreguntaOptional = bancoPreguntasRepository.findById(preguntas.getBancoPreguntas().getId());
        preguntas.setBancoPreguntas(bancoPreguntaOptional.get());
        this.preguntaService.save(preguntas);
        return new ResponseEntity<>(preguntas, HttpStatus.CREATED);
    }

    @GetMapping("/empresa/{idEmpresa}/banco-preguntas/{id}")
    public List<Pregunta> getAll(@PathVariable Long idEmpresa, @PathVariable Long id) {
        return preguntaService.listAll(idEmpresa, id);
    }

    @GetMapping("/{id}/empresa/{idEmpresa}/banco-preguntas/{idBanco}")
    public ResponseEntity<Pregunta> getId(@PathVariable Long idEmpresa, @PathVariable Long idBanco, @PathVariable Long id) {
        Pregunta pregunta = preguntaService.list(idEmpresa, idBanco, id);
        return new ResponseEntity<>(pregunta, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/empresa/{idEmpresa}/banco-preguntas/{idBanco}")
    public ResponseEntity<Pregunta> delete(@PathVariable Long idEmpresa, @PathVariable Long idBanco, @PathVariable Long id) {
        Pregunta pregunta = preguntaService.list(idEmpresa, idBanco, id);
        preguntaService.delete(pregunta);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("pong", HttpStatus.OK);
    }
}
