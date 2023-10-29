package com.proyecto.proyectos.controller;

import com.proyecto.proyectos.model.Proyecto;
import com.proyecto.proyectos.service.ProyectoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proyectos")
public class ProyectoController {

    @Autowired
    private ProyectoService proyectoService;

    @Value("${variable.MicroServicioEmpresa}")
    private String microServicioEmpresa;

    @PostMapping("/")
    public ResponseEntity<Proyecto> post(@Valid @RequestBody Proyecto proyecto, HttpServletRequest request) {
        FindEmpresa findEmpresa = new FindEmpresa();
        Long idEmpresa = findEmpresa.findEmpresa(microServicioEmpresa, request);
        if (idEmpresa != null) {
            proyecto.setIdEmpresa(idEmpresa);
            this.proyectoService.save(proyecto);
            return new ResponseEntity<>(proyecto, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public List<Proyecto> getIdEmpresas(HttpServletRequest request) {
        FindEmpresa findEmpresa = new FindEmpresa();
        Long idEmpresa = findEmpresa.findEmpresa(microServicioEmpresa, request);
        return proyectoService.listEmpresa(idEmpresa);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proyecto> getid(@PathVariable Long id, HttpServletRequest request) {
        FindEmpresa findEmpresa = new FindEmpresa();
        Long idEmpresa = findEmpresa.findEmpresa(microServicioEmpresa, request);
        Proyecto proyecto = proyectoService.list(idEmpresa, id);
        return new ResponseEntity<>(proyecto, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Proyecto> delete(@PathVariable Long id, HttpServletRequest request) {
        FindEmpresa findEmpresa = new FindEmpresa();
        Long idEmpresa = findEmpresa.findEmpresa(microServicioEmpresa, request);
        Proyecto proyecto = proyectoService.list(idEmpresa, id);
        proyectoService.delete(proyecto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("pong", HttpStatus.OK);
    }


}
