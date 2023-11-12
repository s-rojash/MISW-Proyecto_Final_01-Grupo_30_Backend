package com.proyecto.entrevistas.controller;

import com.proyecto.entrevistas.model.AgendaPrueba;
import com.proyecto.entrevistas.model.ResultadoPrueba;
import com.proyecto.entrevistas.security.TokenUtils;
import com.proyecto.entrevistas.service.AgendaPruebaService;
import com.proyecto.entrevistas.service.ResultadoPruebaService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/resultados-pruebas")
public class ResultadosPruebaController {

    @Autowired
    private ResultadoPruebaService resultadoPruebaService;

    @Value("${variable.AccessTokenSecret}")
    private String miAccessTokenSecret;
    
    @PostMapping("/")
    public ResponseEntity<ResultadoPrueba> post(@RequestBody ResultadoPrueba resultadoPrueba, HttpServletRequest request) {
        this.resultadoPruebaService.save(resultadoPrueba);
        return new ResponseEntity<>(resultadoPrueba, HttpStatus.CREATED);
    }

    // @GetMapping("/")
    // public List<AgendaPrueba> getAll(HttpServletRequest request) {
    //     String idEmpresa = TokenUtils.decryptToken(request, miAccessTokenSecret);
    //     return resultadosPruebaService.listAll(Long.valueOf(idEmpresa));
    // }
}
