package com.proyecto.proyectos.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.proyectos.model.Proyecto;
import com.proyecto.proyectos.service.ProyectoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/proyectos")
public class ProyectoController {

    @Autowired
    private ProyectoService proyectoService;

    @Value("${variable.MicroServicioEmpresa}")
    private String microServicioEmpresa;

    private static final String AUTHORIZATION = "Authorization";

    ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/")
    public ResponseEntity<Proyecto> post(@Valid @RequestBody Proyecto proyecto, HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();
        headers.set(AUTHORIZATION, request.getHeader(AUTHORIZATION));
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        if (request.getHeader(AUTHORIZATION).startsWith("Bearer ")) {
            ResponseEntity<String> response = restTemplate.exchange(microServicioEmpresa + "/empresas/me", HttpMethod.GET, requestEntity, String.class);
            String stringJason = response.getBody();
            JsonNode jsonMap;
            try { jsonMap = objectMapper.readTree(stringJason); } catch (JsonProcessingException e) { throw new RuntimeException(e); }
            proyecto.setIdEmpresa(Long.valueOf(String.valueOf(jsonMap.get("id"))));
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.proyectoService.save(proyecto);
        return new ResponseEntity<>(proyecto, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public List<Proyecto> getAll() {
        return proyectoService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proyecto> getid(@PathVariable Long id) {
        Proyecto proyecto = proyectoService.list(id);
        return new ResponseEntity<>(proyecto, HttpStatus.OK);
    }

    @GetMapping("/empresa/{id}")
    public List<Proyecto> getIdEmpresas(@PathVariable Long id) {
        return proyectoService.listEmpresa(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Proyecto> delete(@PathVariable Long id) {
        Proyecto proyecto = proyectoService.list(id);
        proyectoService.delete(proyecto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("pong", HttpStatus.OK);
    }


}
