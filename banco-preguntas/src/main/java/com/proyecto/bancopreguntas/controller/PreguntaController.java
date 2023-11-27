package com.proyecto.bancopreguntas.controller;

import com.proyecto.bancopreguntas.model.BancoPreguntas;
import com.proyecto.bancopreguntas.model.Pregunta;
import com.proyecto.bancopreguntas.repository.BancoPreguntasRepository;
import com.proyecto.bancopreguntas.security.TokenUtils;
import com.proyecto.bancopreguntas.service.PreguntaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/preguntas")
public class PreguntaController {

    @Autowired
    private PreguntaService preguntaService;

    @Autowired(required=false)
    private BancoPreguntasRepository bancoPreguntasRepository;

    @Value("${variable.AccessTokenSecret}")
    private String miAccessTokenSecret;

    @PostMapping("/")
    public ResponseEntity<Pregunta> post(@Valid @RequestBody Pregunta preguntas) {
        Optional<BancoPreguntas> bancoPreguntaOptional = bancoPreguntasRepository.findById(preguntas.getBancoPreguntas().getId());
        if(bancoPreguntaOptional.isPresent()) {
            preguntas.setBancoPreguntas(bancoPreguntaOptional.get());
            this.preguntaService.save(preguntas);
            return new ResponseEntity<>(preguntas, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/banco-preguntas/{id}")
    public List<Pregunta> getAll(HttpServletRequest request, @PathVariable Long id) {
        String idEmpresa = TokenUtils.decryptToken(request, miAccessTokenSecret);
        return preguntaService.listAll(Long.valueOf(idEmpresa), id);
    }

    @GetMapping("/{id}/banco-preguntas/{idBanco}")
    public ResponseEntity<Pregunta> getId(HttpServletRequest request, @PathVariable Long idBanco, @PathVariable Long id) {
        String idEmpresa = TokenUtils.decryptToken(request, miAccessTokenSecret);
        Pregunta pregunta = preguntaService.list(Long.valueOf(idEmpresa), idBanco, id);
        return new ResponseEntity<>(pregunta, HttpStatus.OK);
    }

    @GetMapping("/candidato-banco-preguntas/{id}")
    public List<Pregunta> getAllCandiadto(@PathVariable Long id) {
        return preguntaService.listAllCandidato(id);
    }

    @GetMapping("/{id}/candidato-banco-preguntas/{idBanco}")
    public ResponseEntity<Pregunta> getIdCandidato(@PathVariable Long idBanco, @PathVariable Long id) {
        Pregunta pregunta = preguntaService.listCandidato(idBanco, id);
        return new ResponseEntity<>(pregunta, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/banco-preguntas/{idBanco}")
    public ResponseEntity<Pregunta> delete(HttpServletRequest request, @PathVariable Long idBanco, @PathVariable Long id) {
        String idEmpresa = TokenUtils.decryptToken(request, miAccessTokenSecret);
        Pregunta pregunta = preguntaService.list(Long.valueOf(idEmpresa), idBanco, id);
        preguntaService.delete(pregunta);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("pong", HttpStatus.OK);
    }
}
