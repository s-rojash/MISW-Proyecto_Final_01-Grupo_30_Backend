package com.proyecto.bancopreguntas.controller;

import com.proyecto.bancopreguntas.model.BancoPreguntas;
import com.proyecto.bancopreguntas.model.Pregunta;
import com.proyecto.bancopreguntas.model.Respuesta;
import com.proyecto.bancopreguntas.repository.PreguntaRepository;
import com.proyecto.bancopreguntas.service.RespuestaService;
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
@RequestMapping("/respuestas")
public class RespuestaController {

    @Autowired
    private RespuestaService respuestaService;

    @Autowired(required=false)
    private PreguntaRepository preguntaRepository;

    @Value("${variable.MicroServicioEmpresa}")
    private String microServicioEmpresa;

    @PostMapping("/")
    public ResponseEntity<Respuesta> post(@Valid @RequestBody Respuesta respuesta) {
        Optional<Pregunta> preguntaOptional = preguntaRepository.findById(respuesta.getPregunta().getId());
        respuesta.setPregunta(preguntaOptional.get());
        this.respuestaService.save(respuesta);
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }

    @GetMapping("/banco-preguntas/{idBanco}/pregunta/{idPregunta}")
    public List<Respuesta> getAll(HttpServletRequest request, @PathVariable Long idBanco, @PathVariable Long idPregunta) {
        FindEmpresa findEmpresa = new FindEmpresa();
        Long idEmpresa = findEmpresa.FindEmpresa(microServicioEmpresa, request);
        return respuestaService.listAll(idEmpresa, idBanco, idPregunta);
    }

    @GetMapping("/{id}/banco-preguntas/{idBanco}/pregunta/{idPregunta}")
    public ResponseEntity<Respuesta> getId(HttpServletRequest request, @PathVariable Long idBanco, @PathVariable Long idPregunta, @PathVariable Long id) {
        FindEmpresa findEmpresa = new FindEmpresa();
        Long idEmpresa = findEmpresa.FindEmpresa(microServicioEmpresa, request);
        Respuesta respuesta = respuestaService.list(idEmpresa, idBanco, idPregunta, id);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/banco-preguntas/{idBanco}/pregunta/{idPregunta}")
    public ResponseEntity<Respuesta> delete(HttpServletRequest request, @PathVariable Long idBanco, @PathVariable Long idPregunta, @PathVariable Long id) {
        FindEmpresa findEmpresa = new FindEmpresa();
        Long idEmpresa = findEmpresa.FindEmpresa(microServicioEmpresa, request);
        Respuesta respuesta = respuestaService.list(idEmpresa, idBanco, idPregunta, id);
        respuestaService.delete(respuesta);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("pong", HttpStatus.OK);
    }
}
