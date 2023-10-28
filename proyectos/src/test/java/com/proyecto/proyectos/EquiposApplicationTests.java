package com.proyecto.proyectos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.proyectos.controller.EquipoController;
import com.proyecto.proyectos.model.Equipo;
import com.proyecto.proyectos.model.Perfil;
import com.proyecto.proyectos.model.Proyecto;
import com.proyecto.proyectos.repository.EquipoRepository;
import com.proyecto.proyectos.service.EquipoService;
import com.proyecto.proyectos.service.PerfilService;
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

@WebMvcTest(EquipoController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EquiposApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EquipoService equipoService;

    ObjectMapper objectMapper;
    @BeforeEach
    void config() {
        objectMapper = new ObjectMapper();
    }

//    @Test
//    @DisplayName(value = "Test Controller - Create equipo")
//    @Order(1)
//    void saveEquipo() throws Exception {
//        Perfil perfil = new Perfil(5L, "Perfil 1");
//        Proyecto proyecto = new Proyecto(5L, 1L, "Proyecto 1", "Descripcion 1");
//        Equipo equipo = new Equipo(5L, proyecto, "Equipo 1", perfil, 1);
//
//        when(equipoService.save(any())).then(invocation -> {
//            Equipo u = invocation.getArgument(0);
//            u.setId(5L);
//            return u;
//        });
//
//        mockMvc.perform(post("/equipos/").contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(equipo)))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(5L));
//
//        verify(equipoService).save(any());
//    }

    @Test
    @DisplayName(value = "Test Controller - Get all equipos")
    @Order(2)
    void getAllEquipo() throws Exception {
        Perfil perfil = new Perfil(5L, "Perfil 1");
        Proyecto proyecto = new Proyecto(5L, 1L, "Proyecto 1", "Descripcion 1");
        Equipo equipo = new Equipo(5L, proyecto, "Equipo 1", perfil, 1);

        mockMvc.perform(get("/equipos/empresa/1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Test Controller - Get id equipo")
    @Order(3)
    void getIdEquipo() throws Exception {
        Equipo equipo = new Equipo();

        mockMvc.perform(get("/equipos/1/empresa/1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Test Controller - Get all proyectos")
    @Order(4)
    void getAllProyectos() throws Exception {
        Equipo equipo = new Equipo();

        mockMvc.perform(get("/equipos/empresa/1/proyecto/1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Test Controller - Get id perfiles")
    @Order(5)
    void getIdPerfiles() throws Exception {
        Equipo equipo = new Equipo();

        mockMvc.perform(get("/equipos/empresa/1/perfil/1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Test Controller - Delete id equipo")
    @Order(6)
    void getdeleteEquipo() throws Exception {
        Perfil perfil = new Perfil(5L, "Perfil 1");
        Proyecto proyecto = new Proyecto(5L, 1L, "Proyecto 1", "Descripcion 1");
        Equipo equipo = new Equipo(5L, proyecto, "Equipo 1", perfil, 1);

        when(equipoService.list(proyecto.getIdEmpresa(), equipo.getId())).thenReturn(equipo);

        mockMvc.perform(delete("/equipos/1/empresa/1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Test Controller - Ping pong")
    @Order(7)
    void testPing() throws Exception {
        mockMvc.perform(get("/equipos/ping").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
