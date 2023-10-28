package com.proyecto.proyectos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.proyectos.controller.PerfilController;
import com.proyecto.proyectos.model.Perfil;
import com.proyecto.proyectos.repository.PerfilRepository;
import com.proyecto.proyectos.service.PerfilService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PerfilController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PerfilesApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PerfilService perfilService;

    ObjectMapper objectMapper;
    @BeforeEach
    void config() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName(value = "Test Controller - Create perfil")
    @Order(1)
    void savePerfil() throws Exception {
        Perfil perfil = new Perfil(5L, "Perfil 1");

        when(perfilService.save(any())).then(invocation -> {
            Perfil u = invocation.getArgument(0);
            u.setId(5L);
            return u;
        });

        mockMvc.perform(post("/perfiles/").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(perfil)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(5L));

        verify(perfilService).save(any());
    }

    @Test
    @DisplayName(value = "Test Controller - Get all perfiles")
    @Order(2)
    void getAllPerfiles() throws Exception {
        Perfil perfil = new Perfil();

        mockMvc.perform(get("/perfiles/"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Test Controller - Get id perfil")
    @Order(3)
    void getIdPerfil() throws Exception {
        Perfil perfil = new Perfil();

        mockMvc.perform(get("/perfiles/1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Test Controller - Delete id perfil")
    @Order(4)
    void getdeletePerfil() throws Exception {
        Perfil perfil = new Perfil(5L, "Proyecto 1");

        when(perfilService.list(perfil.getId())).thenReturn(perfil);

        mockMvc.perform(delete("/perfiles/5"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Test Controller - Ping pong")
    @Order(5)
    void testPing() throws Exception {
        mockMvc.perform(get("/perfiles/ping").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
