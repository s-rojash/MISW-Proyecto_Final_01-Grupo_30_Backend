package com.proyecto.entrevistas.controller;

import com.proyecto.entrevistas.model.ResultadoEvaluacionDesempeno;
import com.proyecto.entrevistas.service.ResultadoEvaluacionDesempenoService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/resultados-evaluacion-desepeno")
public class ResultadosEvaluacionDesempenoController {

    @Autowired
    private ResultadoEvaluacionDesempenoService resultadoEvaluacionDesempenoService;

    @Value("${variable.AccessTokenSecret}")
    private String miAccessTokenSecret;
    
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<ResultadoEvaluacionDesempeno> post(@RequestBody ResultadoEvaluacionDesempeno resultadoEvaluacionDesempeno, HttpServletRequest request) {
        this.resultadoEvaluacionDesempenoService.save(resultadoEvaluacionDesempeno);
        return new ResponseEntity<>(resultadoEvaluacionDesempeno, HttpStatus.CREATED);
    }

    // @GetMapping("/")
    // public List<AgendaPrueba> getAll(HttpServletRequest request) {
    //     String idEmpresa = TokenUtils.decryptToken(request, miAccessTokenSecret);
    //     return resultadosPruebaService.listAll(Long.valueOf(idEmpresa));
    // }
}
