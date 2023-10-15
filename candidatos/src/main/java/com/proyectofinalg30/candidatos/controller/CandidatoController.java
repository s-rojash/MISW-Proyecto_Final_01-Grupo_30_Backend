package com.proyectofinalg30.candidatos.controller;

import com.proyectofinalg30.candidatos.model.Candidato;
import com.proyectofinalg30.candidatos.service.CandidatoService;
import com.proyectofinalg30.candidatos.security.TokenUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/candidatos")
public class CandidatoController {

    @Autowired
    private CandidatoService candidatoService;

    @PostMapping("/")
    public ResponseEntity<Candidato> post(@Valid @RequestBody Candidato candidato) {
        String saltNew = BCrypt.gensalt();
        candidato.setPassword(BCrypt.hashpw(candidato.getPassword(), saltNew));
        candidato.setCreatedAt(new Date());

        this.candidatoService.save(candidato);
        return new ResponseEntity<>(candidato, HttpStatus.CREATED);
    }

    @PostMapping("/auth")
    public ResponseEntity<Candidato> postAuth(@RequestBody Candidato authCredentials) {
        if (authCredentials.getEmail() != "" && authCredentials.getEmail() != null && authCredentials.getPassword() != "" && authCredentials.getPassword() != null){
            Candidato candidato = candidatoService.searchEmail(authCredentials.getEmail());
            if (BCrypt.checkpw(authCredentials.getPassword(), candidato.getPassword())){
                candidato.setToken(TokenUtils.createToken(candidato.getId()));
                candidato.setExpireAt(new Date(System.currentTimeMillis() + 1_800_000));
                candidatoService.save(candidato);
                return new ResponseEntity<>(candidato, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/me")
    public ResponseEntity<Candidato> get(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken.startsWith("Bearer ")) {
            Candidato user = candidatoService.searchToken(bearerToken.replace("Bearer ", ""));
            if (new Date().before(user.getExpireAt())){
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public List<Candidato> getAll() {
        return candidatoService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidato> get(@PathVariable Long id) {
        Candidato candidato = candidatoService.list(id);
        return new ResponseEntity<>(candidato, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Candidato> delete(@PathVariable Long id) {
        Candidato candidato = candidatoService.list(id);
        candidatoService.delete(candidato);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("pong", HttpStatus.OK);
    }
}
