package com.proyecto.entrevistas.controller;

import com.proyecto.entrevistas.model.ResultadoPrueba;
import com.proyecto.entrevistas.service.ResultadoPruebaService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/resultados-pruebas")
public class ResultadosPruebaController {

    @Autowired
    private ResultadoPruebaService resultadoPruebaService;

    @Value("${variable.AccessTokenSecret}")
    private String miAccessTokenSecret;
    
    @PostMapping("/")
    @ResponseBody
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
