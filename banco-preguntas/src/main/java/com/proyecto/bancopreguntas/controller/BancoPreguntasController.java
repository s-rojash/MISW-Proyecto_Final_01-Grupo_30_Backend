package com.proyecto.bancopreguntas.controller;

import com.proyecto.bancopreguntas.model.BancoPreguntas;
import com.proyecto.bancopreguntas.security.TokenUtils;
import com.proyecto.bancopreguntas.service.BancoPreguntasService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/banco-preguntas")
public class BancoPreguntasController {

    @Autowired
    private BancoPreguntasService bancoPreguntasService;

    @Value("${variable.AccessTokenSecret}")
    private String miAccessTokenSecret;

    @PostMapping("/")
    public ResponseEntity<BancoPreguntas> post(@Valid @RequestBody BancoPreguntas bancoPreguntas, HttpServletRequest request) {
        String idEmpresa = TokenUtils.decryptToken(request, miAccessTokenSecret);
        if (idEmpresa != null) {
            bancoPreguntas.setIdEmpresa(Long.valueOf(idEmpresa));
            this.bancoPreguntasService.save(bancoPreguntas);
            return new ResponseEntity<>(bancoPreguntas, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public List<BancoPreguntas> getAll(HttpServletRequest request) {
        String idEmpresa = TokenUtils.decryptToken(request, miAccessTokenSecret);
        return bancoPreguntasService.listAll(Long.valueOf(idEmpresa));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BancoPreguntas> getId(HttpServletRequest request, @PathVariable Long id) {
        String idEmpresa = TokenUtils.decryptToken(request, miAccessTokenSecret);
        BancoPreguntas bancoPreguntas = bancoPreguntasService.list(Long.valueOf(idEmpresa), id);
        return new ResponseEntity<>(bancoPreguntas, HttpStatus.OK);
    }

    @GetMapping("/tipo-banco/{tipoBanco}")
    public List<BancoPreguntas> getAllProyecto(HttpServletRequest request, @PathVariable String tipoBanco) {
        String idEmpresa = TokenUtils.decryptToken(request, miAccessTokenSecret);
        return bancoPreguntasService.listAllTipobanco(Long.valueOf(idEmpresa), tipoBanco);
    }

    @GetMapping("/categoria/{id}")
    public List<BancoPreguntas> getAllPerfil(HttpServletRequest request, @PathVariable Long id) {
        String idEmpresa = TokenUtils.decryptToken(request, miAccessTokenSecret);
        return bancoPreguntasService.listAllCategorias(Long.valueOf(idEmpresa), id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BancoPreguntas> delete(HttpServletRequest request, @PathVariable Long id) {
        String idEmpresa = TokenUtils.decryptToken(request, miAccessTokenSecret);
        BancoPreguntas bancoPreguntas = bancoPreguntasService.list(Long.valueOf(idEmpresa), id);
        bancoPreguntasService.delete(bancoPreguntas);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("pong", HttpStatus.OK);
    }
}
