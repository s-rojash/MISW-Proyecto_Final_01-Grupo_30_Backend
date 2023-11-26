package com.proyecto.presentarprueba.controller;

import com.proyecto.presentarprueba.model.PresentarPrueba;
import com.proyecto.presentarprueba.security.TokenUtils;
import com.proyecto.presentarprueba.service.PresentarPruebaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/presentar-prueba")
public class PresentarPruebaController {

    @Autowired
    private PresentarPruebaService presentarPruebaService;

    @Value("${variable.AccessTokenSecret}")
    private String miAccessTokenSecret;

    @PostMapping("/")
    public ResponseEntity<PresentarPrueba> post(@Valid @RequestBody PresentarPrueba presentarPrueba) {
        this.presentarPruebaService.save(presentarPrueba);
        return new ResponseEntity<>(presentarPrueba, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public List<PresentarPrueba> getAll() {
        return presentarPruebaService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PresentarPrueba> getId(HttpServletRequest request, @PathVariable Long id) {
        String idCandidato = TokenUtils.decryptToken(request, miAccessTokenSecret);
        PresentarPrueba presentarPrueba = presentarPruebaService.listId(id, Long.valueOf(idCandidato));
        return new ResponseEntity<>(presentarPrueba, HttpStatus.OK);
    }

    @GetMapping("/prueba/{id}")
    public List<PresentarPrueba> getIdPrueba(HttpServletRequest request, @PathVariable Long id) {
        String idCandidato = TokenUtils.decryptToken(request, miAccessTokenSecret);
        return presentarPruebaService.listParametros(id, Long.valueOf(idCandidato));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PresentarPrueba> delete(HttpServletRequest request, @PathVariable Long id) {
        String idCandidato = TokenUtils.decryptToken(request, miAccessTokenSecret);
        PresentarPrueba presentarPrueba = presentarPruebaService.listId(id, Long.valueOf(idCandidato));
        presentarPruebaService.delete(presentarPrueba);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("pong", HttpStatus.OK);
    }
}
