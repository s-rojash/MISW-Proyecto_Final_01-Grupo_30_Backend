package com.proyecto.bancopreguntas.controller;

import com.proyecto.bancopreguntas.model.Prueba;
import com.proyecto.bancopreguntas.model.PruebaCandidato;
import com.proyecto.bancopreguntas.repository.PruebaRepository;
import com.proyecto.bancopreguntas.security.EmailService;
import com.proyecto.bancopreguntas.security.TokenUtils;
import com.proyecto.bancopreguntas.service.PruebaCandidatoService;
import com.proyecto.bancopreguntas.service.PruebaService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/pruebas-candidato")
public class PruebaCandidatoController {

    @Autowired
    private PruebaCandidatoService pruebaCandidatoService;

    @Value("${variable.AccessTokenSecret}")
    private String miAccessTokenSecret;

    @Autowired(required=false)
    private PruebaRepository pruebaRepository;

    @Autowired
    private EmailService emailService;

    @PostMapping("/")
    public ResponseEntity<PruebaCandidato> post(@RequestBody PruebaCandidato pruebaCandidato) {
        Optional<Prueba> pruebaOptional = pruebaRepository.findById(pruebaCandidato.getPrueba().getId());
        if(pruebaOptional.isPresent()) {
            pruebaCandidato.setPrueba(pruebaOptional.get());
            this.pruebaCandidatoService.save(pruebaCandidato);

//            String destinatario = "sarojas04@hotmail.com";
//            String asunto = "Asunto del correo";
//            String cuerpo = "Cuerpo del correo";
//
//            emailService.enviarCorreo(destinatario, asunto, cuerpo);

            return new ResponseEntity<>(pruebaCandidato, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public List<PruebaCandidato> getAll(HttpServletRequest request) {
        String idEmpresa = TokenUtils.decryptToken(request, miAccessTokenSecret);
        return pruebaCandidatoService.listAll(Long.valueOf(idEmpresa));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PruebaCandidato> getid(@PathVariable Long id) {
        PruebaCandidato pruebaCandidato = pruebaCandidatoService.list(id);
        return new ResponseEntity<>(pruebaCandidato, HttpStatus.OK);
    }

    @GetMapping("/candidato/{id}")
    public List<PruebaCandidato> getidCandidato(@PathVariable Long id) {
        return pruebaCandidatoService.listCandidato(id);
    }

    @GetMapping("/prueba/{id}")
    public List<PruebaCandidato> getidPrueba(@PathVariable Long id) {
        return pruebaCandidatoService.listPrueba(id);
    }

    @GetMapping("/estado")
    public List<PruebaCandidato> getEstado(@RequestParam("estados") List<String> estados, HttpServletRequest request) {
        String idEmpresa = TokenUtils.decryptToken(request, miAccessTokenSecret);
        return pruebaCandidatoService.listEstado(Long.valueOf(idEmpresa), estados);
    }

    @GetMapping("/fecha")
    public List<PruebaCandidato> getFecha(@RequestParam("fechaIncio") String fechaInicio, @RequestParam("fechaFin") String fechaFin, HttpServletRequest request) {
        String idEmpresa = TokenUtils.decryptToken(request, miAccessTokenSecret);
        return pruebaCandidatoService.listFecha(Long.valueOf(idEmpresa), fechaInicio, fechaFin);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<PruebaCandidato> delete(@PathVariable Long id) {
        PruebaCandidato pruebaCandidato = pruebaCandidatoService.list(id);
        pruebaCandidatoService.delete(pruebaCandidato);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("pong", HttpStatus.OK);
    }
    
}
