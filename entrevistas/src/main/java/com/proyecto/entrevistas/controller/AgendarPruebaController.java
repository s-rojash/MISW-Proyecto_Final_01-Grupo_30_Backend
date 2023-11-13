package com.proyecto.bancopreguntas.controller;

import com.proyecto.bancopreguntas.model.AgendarPrueba;
import com.proyecto.bancopreguntas.security.TokenUtils;
import com.proyecto.bancopreguntas.service.AgendarPruebaService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.val;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendar-pruebas")
public class AgendarPruebaController {

    @Autowired
    private AgendarPruebaService agendarPruebaService;

    @Value("${variable.AccessTokenSecret}")
    private String miAccessTokenSecret;
    
    @PostMapping("/")
    public ResponseEntity<AgendarPrueba> post(@RequestBody AgendarPrueba agendarPrueba) {
        this.agendarPruebaService.save(agendarPrueba);
        return new ResponseEntity<>(agendarPrueba, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public List<AgendarPrueba> getAll(HttpServletRequest request) {
        String idEmpresa = TokenUtils.decryptToken(request, miAccessTokenSecret);
        return agendarPruebaService.listAll(Long.valueOf(idEmpresa));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendarPrueba> getid(@PathVariable Long id, HttpServletRequest request) {
        String idEmpresa = TokenUtils.decryptToken(request, miAccessTokenSecret);
        AgendarPrueba agendarPrueba = agendarPruebaService.list(Long.valueOf(idEmpresa), id);
        return new ResponseEntity<>(agendarPrueba, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AgendarPrueba> delete(@PathVariable Long id, HttpServletRequest request) {
        String idEmpresa = TokenUtils.decryptToken(request, miAccessTokenSecret);
        AgendarPrueba agendarPrueba = agendarPruebaService.list(Long.valueOf(idEmpresa), id);
        agendarPruebaService.delete(agendarPrueba);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("pong", HttpStatus.OK);
    }
}
