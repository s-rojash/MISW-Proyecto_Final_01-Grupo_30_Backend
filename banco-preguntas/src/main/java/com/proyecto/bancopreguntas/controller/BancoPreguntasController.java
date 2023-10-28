package com.proyecto.bancopreguntas.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.bancopreguntas.model.BancoPreguntas;
import com.proyecto.bancopreguntas.model.Categoria;
import com.proyecto.bancopreguntas.repository.CategoriaRepository;
import com.proyecto.bancopreguntas.service.BancoPreguntasService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/banco-preguntas")
public class BancoPreguntasController {

    @Autowired
    private BancoPreguntasService bancoPreguntasService;

    @Value("${variable.MicroServicioEmpresa}")
    private String microServicioEmpresa;

    private static final String AUTHORIZATION = "Authorization";

    @Autowired(required=false)
    private CategoriaRepository categoriaRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/")
    public ResponseEntity<BancoPreguntas> post(@Valid @RequestBody BancoPreguntas bancoPreguntas, HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();
        headers.set(AUTHORIZATION, request.getHeader(AUTHORIZATION));
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        if (request.getHeader(AUTHORIZATION).startsWith("Bearer ")) {
            ResponseEntity<String> response = restTemplate.exchange(microServicioEmpresa + "/empresas/me", HttpMethod.GET, requestEntity, String.class);
            String stringJason = response.getBody();
            JsonNode jsonMap;
            try { jsonMap = objectMapper.readTree(stringJason); } catch (JsonProcessingException e) { throw new RuntimeException(e); }
            bancoPreguntas.setIdEmpresa(Long.valueOf(String.valueOf(jsonMap.get("id"))));

            Optional<Categoria> categoriaOptional = categoriaRepository.findById(bancoPreguntas.getCategoria().getId());
            bancoPreguntas.setCategoria(categoriaOptional.get());
            this.bancoPreguntasService.save(bancoPreguntas);
            return new ResponseEntity<>(bancoPreguntas, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/empresa/{idEmpresa}")
    public List<BancoPreguntas> getAll(@PathVariable Long idEmpresa) {
        return bancoPreguntasService.listAll(idEmpresa);
    }

    @GetMapping("/{id}/empresa/{idEmpresa}")
    public ResponseEntity<BancoPreguntas> getId(@PathVariable Long idEmpresa, @PathVariable Long id) {
        BancoPreguntas bancoPreguntas = bancoPreguntasService.list(idEmpresa, id);
        return new ResponseEntity<>(bancoPreguntas, HttpStatus.OK);
    }

    @GetMapping("/empresa/{idEmpresa}/tipo-banco/{tipoBanco}")
    public List<BancoPreguntas> getAllProyecto(@PathVariable Long idEmpresa, @PathVariable String tipoBanco) {
        return bancoPreguntasService.listAllTipobanco(idEmpresa, tipoBanco);
    }

    @GetMapping("/empresa/{idEmpresa}/categoria/{id}")
    public List<BancoPreguntas> getAllPerfil(@PathVariable Long idEmpresa, @PathVariable Long id) {
        return bancoPreguntasService.listAllCategorias(idEmpresa, id);
    }

    @DeleteMapping("/{id}/empresa/{idEmpresa}")
    public ResponseEntity<BancoPreguntas> delete(@PathVariable Long idEmpresa, Long id) {
        BancoPreguntas bancoPreguntas = bancoPreguntasService.list(idEmpresa, id);
        bancoPreguntasService.delete(bancoPreguntas);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("pong", HttpStatus.OK);
    }
}
