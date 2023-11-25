package com.proyectofinalg30.candidatos.controller;

import com.proyectofinalg30.candidatos.model.Candidato;
import com.proyectofinalg30.candidatos.model.CandidatoHabilidad;
import com.proyectofinalg30.candidatos.model.Habilidad;
import com.proyectofinalg30.candidatos.repository.CandidatoRepository;
import com.proyectofinalg30.candidatos.repository.HabilidadRepository;
import com.proyectofinalg30.candidatos.service.CandidatoHabilidadService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/candidato-habilidades")
public class CandidatoHabilidadController {

    @Autowired
    private CandidatoHabilidadService candidatoHabilidadService;

    @Autowired(required=false)
    private CandidatoRepository candidatoRepository;

    @Autowired(required=false)
    private HabilidadRepository habilidadRepository;
    @PostMapping("/")
    public ResponseEntity<?> post(@Valid @RequestBody CandidatoHabilidad candidatoHabilidad) {
        Optional<Candidato> candidatoOptional = candidatoRepository.findById(candidatoHabilidad.getCandidato().getId());
        Optional<Habilidad> habilidadOptional = habilidadRepository.findById(candidatoHabilidad.getHabilidad().getId());
        if(candidatoOptional.isPresent() && habilidadOptional.isPresent()) {
            candidatoHabilidad.setCandidato(candidatoOptional.get());
            candidatoHabilidad.setHabilidad(habilidadOptional.get());
            this.candidatoHabilidadService.save(candidatoHabilidad);
            return new ResponseEntity<>(candidatoHabilidad, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public List<CandidatoHabilidad> getAll() {
        return candidatoHabilidadService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidatoHabilidad> get(@PathVariable Long id) {
        CandidatoHabilidad candidatoHabilidad = candidatoHabilidadService.list(id);
        return new ResponseEntity<>(candidatoHabilidad, HttpStatus.OK);
    }

    @GetMapping("/habilidades/{id}")
    public List<CandidatoHabilidad> getTipoHabilidad(@PathVariable Long id) {
        return candidatoHabilidadService.searchHabilidad(id);
    }

    @GetMapping("/habilidades")
    public List<CandidatoHabilidad> getTipoHabilidad2(@RequestParam("habilidades") List<Long> habilidades) {
        return candidatoHabilidadService.searchHabilidadBusqueda(habilidades);
    }

    @GetMapping("/candidatos/{id}")
    public List<CandidatoHabilidad> getCandidato(@PathVariable Long id) {
        return candidatoHabilidadService.searchCandidato(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CandidatoHabilidad> delete(@PathVariable Long id) {
        CandidatoHabilidad candidatoHabilidad = candidatoHabilidadService.list(id);
        candidatoHabilidadService.delete(candidatoHabilidad);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("pong", HttpStatus.OK);
    }
}
