package com.proyecto.proyectos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.proyectos.controller.ProyectoController;
import com.proyecto.proyectos.model.Proyecto;
import com.proyecto.proyectos.repository.ProyectoRepository;
import com.proyecto.proyectos.service.ProyectoService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProyectoController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProyectosApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProyectoService proyectoService;
    ObjectMapper objectMapper;
    @BeforeEach
    void config() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName(value = "Test Controller - Create proyecto")
    @Order(1)
    void saveProyecto() throws Exception {
        Proyecto proyecto = new Proyecto(5L, 1L, "Proyecto 1", "Descripcion 1");
        when(proyectoService.save(any())).then(invocation -> {
            Proyecto u = invocation.getArgument(0);
            u.setId(5L);
            return u;
        });

        mockMvc.perform(post("/proyectos/").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(proyecto))
                        .header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2OTg1MjUzNTcsInN1YiI6IjciLCJuYmYiOjE2OTg1MjUzNTcsImV4cCI6MTcwMDMyNTM1NywidHlwZSI6ImFjY2VzcyIsImZyZXNoIjpmYWxzZX0.S8N4k_OuT9ttCWovS5Xok-2LcaUuHWYDgOo_YfY1hac"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(5L))
                .andExpect(jsonPath("$.nombre").value("Proyecto 1"));

        verify(proyectoService).save(any());
    }

    @Test
    @DisplayName(value = "Test Controller - Get all proyectos")
    @Order(2)
    void getAllProyectos() throws Exception {
        Proyecto proyecto = new Proyecto();
        proyecto = new Proyecto(5L, 1L, "Proyecto 1", "Descripcion 1");

        when(proyectoService.searchNombre(proyecto.getNombre())).thenReturn(proyecto);

        mockMvc.perform(get("/proyectos/"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Test Controller - Get id proyectos")
    @Order(3)
    void getIdProyectos() throws Exception {
        Proyecto proyecto = new Proyecto();
        proyecto = new Proyecto(5L, 1L, "Proyecto 1", "Descripcion 1");

        when(proyectoService.list(proyecto.getId())).thenReturn(proyecto);

        mockMvc.perform(get("/proyectos/5"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Test Controller - Get id empresa proyectos")
    @Order(4)
    void getIdEmpresaProyectos() throws Exception {
        Proyecto proyecto = new Proyecto();

        when(proyectoService.searchNombre(proyecto.getNombre())).thenReturn(proyecto);

        mockMvc.perform(get("/proyectos/empresa/1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Test Controller - Delete id proyecto")
    @Order(5)
    void getdeleteProyecto() throws Exception {
        Proyecto proyecto = new Proyecto(5L, 1L, "Proyecto 1", "Descripcion 1");

        when(proyectoService.list(proyecto.getId())).thenReturn(proyecto);

        mockMvc.perform(delete("/proyectos/5"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Test Controller - Ping pong")
    @Order(6)
    void testPing() throws Exception {
        mockMvc.perform(get("/proyectos/ping").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
