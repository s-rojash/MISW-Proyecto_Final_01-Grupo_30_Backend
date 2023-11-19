package com.proyecto.proyectos.controller;

import com.proyecto.proyectos.model.Proyecto;
import com.proyecto.proyectos.security.TokenUtils;
import com.proyecto.proyectos.service.ProyectoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping("/proyectos")
public class ProyectoController {

    @Autowired
    private ProyectoService proyectoService;

    @Value("${variable.AccessTokenSecret}")
    private String miAccessTokenSecret;

    @PostMapping("/")
    public ResponseEntity<Proyecto> post(@Valid @RequestBody Proyecto proyecto, HttpServletRequest request) {
        String idEmpresa = TokenUtils.decryptToken(request, miAccessTokenSecret);
        if (idEmpresa != null) {
            proyecto.setIdEmpresa(Long.valueOf(idEmpresa));
            this.proyectoService.save(proyecto);
            return new ResponseEntity<>(proyecto, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public List<Proyecto> getIdEmpresas(HttpServletRequest request) {
        String idEmpresa = TokenUtils.decryptToken(request, miAccessTokenSecret);
        return proyectoService.listEmpresa(Long.valueOf(idEmpresa));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proyecto> getid(@PathVariable Long id, HttpServletRequest request) {
        String idEmpresa = TokenUtils.decryptToken(request, miAccessTokenSecret);
        Proyecto proyecto = proyectoService.list(Long.valueOf(idEmpresa), id);
        return new ResponseEntity<>(proyecto, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Proyecto> delete(@PathVariable Long id, HttpServletRequest request) {
        String idEmpresa = TokenUtils.decryptToken(request, miAccessTokenSecret);
        Proyecto proyecto = proyectoService.list(Long.valueOf(idEmpresa), id);
        proyectoService.delete(proyecto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("pong", HttpStatus.OK);
    }


}
