package com.proyecto.bancopreguntas.controller;

import com.proyecto.bancopreguntas.security.TokenUtils;
import com.proyecto.bancopreguntas.service.PruebaService;
import com.proyecto.bancopreguntas.model.Prueba;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/pruebas")
public class PruebaController {

    @Autowired
    private PruebaService pruebaService;

    @Value("${variable.AccessTokenSecret}")
    private String miAccessTokenSecret;

    @PostMapping("/")
    public ResponseEntity<Prueba> post(@RequestBody Prueba prueba) {
        this.pruebaService.save(prueba);
        return new ResponseEntity<>(prueba, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public List<Prueba> getAll(HttpServletRequest request) {
        String idEmpresa = TokenUtils.decryptToken(request, miAccessTokenSecret);
        return pruebaService.listAll(Long.valueOf(idEmpresa));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prueba> getid(@PathVariable Long id, HttpServletRequest request) {
        String idEmpresa = TokenUtils.decryptToken(request, miAccessTokenSecret);
        Prueba prueba = pruebaService.list(Long.valueOf(idEmpresa), id);
        return new ResponseEntity<>(prueba, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Prueba> delete(@PathVariable Long id, HttpServletRequest request) {
        String idEmpresa = TokenUtils.decryptToken(request, miAccessTokenSecret);
        Prueba prueba = pruebaService.list(Long.valueOf(idEmpresa), id);
        pruebaService.delete(prueba);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("pong", HttpStatus.OK);
    }
    
}
