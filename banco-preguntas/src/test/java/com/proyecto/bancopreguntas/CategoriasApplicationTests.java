package com.proyecto.bancopreguntas;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.bancopreguntas.controller.CategoriaController;
import com.proyecto.bancopreguntas.model.Categoria;
import com.proyecto.bancopreguntas.service.CategoriaService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoriaController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategoriasApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoriaService categoriaService;

    ObjectMapper objectMapper;
    @BeforeEach
    void config() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName(value = "Test Controller - Create categoria")
    @Order(1)
    void savePerfil() throws Exception {
        Categoria perfil = new Categoria(5L, "Categoria 1");

        when(categoriaService.save(any())).then(invocation -> {
            Categoria u = invocation.getArgument(0);
            u.setId(5L);
            return u;
        });

        mockMvc.perform(post("/categorias/").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(perfil)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(5L));

        verify(categoriaService).save(any());
    }

    @Test
    @DisplayName(value = "Test Controller - Get all categorias")
    @Order(2)
    void getAllPerfiles() throws Exception {
        Categoria categoria = new Categoria();

        mockMvc.perform(get("/categorias/"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Test Controller - Get id categoria")
    @Order(3)
    void getIdPerfil() throws Exception {
        Categoria categoria = new Categoria();

        mockMvc.perform(get("/categorias/1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Test Controller - Delete id categoria")
    @Order(4)
    void getdeletePerfil() throws Exception {
        Categoria categoria = new Categoria(5L, "Categoria 1");

        when(categoriaService.list(categoria.getId())).thenReturn(categoria);

        mockMvc.perform(delete("/categorias/5"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Test Controller - Ping pong")
    @Order(5)
    void testPing() throws Exception {
        mockMvc.perform(get("/categorias/ping").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
