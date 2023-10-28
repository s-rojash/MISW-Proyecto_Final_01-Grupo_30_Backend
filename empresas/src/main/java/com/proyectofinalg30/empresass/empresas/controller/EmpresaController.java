package com.proyectofinalg30.empresass.empresas.controller;

import com.proyectofinalg30.empresass.empresas.security.TokenUtils;
import com.proyectofinalg30.empresass.empresas.model.Empresa;
import com.proyectofinalg30.empresass.empresas.service.EmpresaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @Value("${variable.AccessTokenSecret}")
    private String miAccessTokenSecret;

    @PostMapping("/")
    public ResponseEntity<Empresa> post(@Valid @RequestBody Empresa empresa) {
        String saltNew = BCrypt.gensalt();
        empresa.setPassword(BCrypt.hashpw(empresa.getPassword(), saltNew));
        empresa.setCreatedAt(new Date());

        this.empresaService.save(empresa);
        return new ResponseEntity<>(empresa, HttpStatus.CREATED);
    }

    @PostMapping("/auth")
    public ResponseEntity<Empresa> postAuth(@RequestBody Empresa authCredentials) {
        if (authCredentials.getEmail() != null && authCredentials.getPassword() != null){
            Empresa empresa = empresaService.searchEmail(authCredentials.getEmail());
            if (BCrypt.checkpw(authCredentials.getPassword(), empresa.getPassword())){
                empresa.setToken(TokenUtils.createToken(empresa.getId(), miAccessTokenSecret));
                empresa.setExpireAt(new Date(System.currentTimeMillis() + 1_800_000));
                empresaService.save(empresa);
                return new ResponseEntity<>(empresa, HttpStatus.OK);
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
    public ResponseEntity<Empresa> get(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken.startsWith("Bearer ")) {
            Empresa empresa = empresaService.searchToken(bearerToken.replace("Bearer ", ""));
            if (new Date().before(empresa.getExpireAt())){
                return new ResponseEntity<>(empresa, HttpStatus.OK);
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
    public List<Empresa> getAll() {
        return empresaService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> get(@PathVariable Long id) {
        Empresa empresa = empresaService.list(id);
        return new ResponseEntity<>(empresa, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Empresa> delete(@PathVariable Long id) {
        Empresa empresa = empresaService.list(id);
        empresaService.delete(empresa);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("pong", HttpStatus.OK);
    }
}
