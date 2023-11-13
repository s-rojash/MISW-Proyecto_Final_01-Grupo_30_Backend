package com.proyecto.entrevistas.controller;

import com.proyecto.entrevistas.model.AgendaPrueba;
import com.proyecto.entrevistas.security.TokenUtils;
import com.proyecto.entrevistas.service.AgendaPruebaService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/agenda-pruebas")
public class AgendaPruebaController {

    @Autowired
    private AgendaPruebaService agendaPruebaService;

    @Value("${variable.AccessTokenSecret}")
    private String miAccessTokenSecret;
    
    @PostMapping("/")
    public ResponseEntity<AgendaPrueba> post(@RequestBody AgendaPrueba agendaPrueba, HttpServletRequest request) {
        String idEmpresa = TokenUtils.decryptToken(request, miAccessTokenSecret);
        agendaPrueba.setIdEmpresa(Long.parseLong(idEmpresa));
        this.agendaPruebaService.save(agendaPrueba);
        return new ResponseEntity<>(agendaPrueba, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public List<AgendaPrueba> getAll(HttpServletRequest request) {
        String idEmpresa = TokenUtils.decryptToken(request, miAccessTokenSecret);
        return agendaPruebaService.listAll(Long.valueOf(idEmpresa));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaPrueba> getid(@PathVariable Long id, HttpServletRequest request) {
        String idEmpresa = TokenUtils.decryptToken(request, miAccessTokenSecret);
        AgendaPrueba AgendaPrueba = agendaPruebaService.list(Long.valueOf(idEmpresa), id);
        return new ResponseEntity<>(AgendaPrueba, HttpStatus.OK);
    }

}
