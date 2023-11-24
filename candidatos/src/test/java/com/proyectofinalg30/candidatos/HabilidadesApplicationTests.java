package com.proyectofinalg30.candidatos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyectofinalg30.candidatos.controller.HabilidadController;
import com.proyectofinalg30.candidatos.model.Candidato;
import com.proyectofinalg30.candidatos.model.Habilidad;
import com.proyectofinalg30.candidatos.repository.CandidatoRepository;
import com.proyectofinalg30.candidatos.repository.HabilidadRepository;
import com.proyectofinalg30.candidatos.service.CandidatoService;
import com.proyectofinalg30.candidatos.service.HabilidadService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HabilidadController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HabilidadesApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HabilidadService habilidadService;
    private HabilidadRepository habilidadRepository;
    ObjectMapper objectMapper;
    @BeforeEach
    void config() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName(value = "Test Controller - Create habilidades")
    @Order(1)
    void saveHabilidad() throws Exception {
        Habilidad habilidad = new Habilidad(1L,"Blandas","Puntualidad");
        when(habilidadService.save(any())).then(invocation -> {
            Habilidad u = invocation.getArgument(0);
            u.setId(1L);
            return u;
        });

        mockMvc.perform(post("/habilidades/").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(habilidad)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.tipoHabilidad").value("Blandas"));

        verify(habilidadService).save(any());
    }


    @Test
    @DisplayName(value = "Test Controller - Get all habilidades")
    @Order(2)
    void getAllHabilidades() throws Exception {
        Habilidad habilidad = new Habilidad(1L, "Blandas","Puntualidad");

        when(habilidadService.list(habilidad.getId())).thenReturn(habilidad);

        mockMvc.perform(get("/habilidades/"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Test Controller - Get id habilidad")
    @Order(3)
    void getIdHabilidad() throws Exception {
        Habilidad habilidad = new Habilidad(1L, "Blandas","Puntualidad");

        when(habilidadService.list(habilidad.getId())).thenReturn(habilidad);

        mockMvc.perform(get("/habilidades/1"))
                .andExpect(status().isOk());
    }



    @Test
    @DisplayName(value = "Test Controller - Delete id habilidad")
    @Order(6)
    void getdeleteCandidatos() throws Exception {
        Habilidad habilidad = new Habilidad(1L, "Blandas","Puntualidad");

        when(habilidadService.list(habilidad.getId())).thenReturn(habilidad);

        mockMvc.perform(delete("/habilidades/1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Test Controller - Ping pong")
    @Order(7)
    void testPing() throws Exception {
        mockMvc.perform(get("/habilidades/ping").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
