package com.proyecto.proyectos.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Configuration
public class FindEmpresa {

    private static final String AUTHORIZATION = "Authorization";

    ObjectMapper objectMapper = new ObjectMapper();

    public Long FindEmpresa(String URL, HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();
        headers.set(AUTHORIZATION, request.getHeader(AUTHORIZATION));
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        if (request.getHeader(AUTHORIZATION).startsWith("Bearer ")) {
            System.out.println("Cuerpo request: "+ requestEntity);
            System.out.println("URL: "+ URL);
            ResponseEntity<String> response = restTemplate.exchange(URL + "/empresas/me", HttpMethod.GET, requestEntity, String.class);
            String stringJason = response.getBody();
            JsonNode jsonMap;
            try { jsonMap = objectMapper.readTree(stringJason); } catch (JsonProcessingException e) { throw new RuntimeException(e); }
            Long idEmpresa = Long.valueOf(String.valueOf(jsonMap.get("id")));
            return idEmpresa;
        }
        else {
            return null;
        }
    }
}
