package com.proyecto.proyectos.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Configuration
public class FindEmpresa {

    private static final String AUTHORIZATION = "Authorization";

    ObjectMapper objectMapper = new ObjectMapper();

    public Long findEmpresa(String url, HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();
        headers.set(AUTHORIZATION, request.getHeader(AUTHORIZATION));
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        if (request.getHeader(AUTHORIZATION).startsWith("Bearer ")) {
            ResponseEntity<String> response = restTemplate.exchange(url + "/empresas/me", HttpMethod.GET, requestEntity, String.class);
            String stringJason = response.getBody();
            JsonNode jsonMap;
            try { jsonMap = objectMapper.readTree(stringJason); } catch (JsonProcessingException e) { throw new RuntimeException(e); }
            return Long.valueOf(String.valueOf(jsonMap.get("id")));
        }
        else {
            return null;
        }
    }
}
