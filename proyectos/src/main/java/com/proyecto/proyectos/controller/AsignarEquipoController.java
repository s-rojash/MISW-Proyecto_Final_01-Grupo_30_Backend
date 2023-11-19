package com.proyecto.proyectos.controller;

import com.proyecto.proyectos.model.AsignarEquipo;
import com.proyecto.proyectos.model.Perfil;
import com.proyecto.proyectos.security.TokenUtils;
import com.proyecto.proyectos.service.AsignarEquipoService;
import com.proyecto.proyectos.service.PerfilService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping("/asignar-equipo")
public class AsignarEquipoController {

    @Autowired
    private AsignarEquipoService asignarEquipoService;

    @Value("${variable.AccessTokenSecret}")
    private String miAccessTokenSecret;

    @PostMapping("/")
    public ResponseEntity<AsignarEquipo> post(@Valid @RequestBody AsignarEquipo asignarEquipo) {
        this.asignarEquipoService.save(asignarEquipo);
        return new ResponseEntity<>(asignarEquipo, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public List<AsignarEquipo> getAll(HttpServletRequest request) {
        String idEmpresa = TokenUtils.decryptToken(request, miAccessTokenSecret);
        return asignarEquipoService.listAll(Long.valueOf(idEmpresa));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsignarEquipo> getid(@PathVariable Long id, HttpServletRequest request) {
        String idEmpresa = TokenUtils.decryptToken(request, miAccessTokenSecret);
        AsignarEquipo asignarEquipo = asignarEquipoService.list(Long.valueOf(idEmpresa), id);
        return new ResponseEntity<>(asignarEquipo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AsignarEquipo> delete(@PathVariable Long id, HttpServletRequest request) {
        String idEmpresa = TokenUtils.decryptToken(request, miAccessTokenSecret);
        AsignarEquipo asignarEquipo = asignarEquipoService.list(Long.valueOf(idEmpresa), id);
        asignarEquipoService.delete(asignarEquipo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("pong", HttpStatus.OK);
    }
}
