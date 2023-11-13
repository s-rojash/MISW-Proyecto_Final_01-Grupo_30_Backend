package com.proyecto.entrevistas;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.entrevistas.controller.ResultadosPruebaController;
import com.proyecto.entrevistas.model.ResultadoPrueba;
import com.proyecto.entrevistas.repository.ResultadoPruebaRepository;
import com.proyecto.entrevistas.repository.ResultadoPruebaRepository;
import com.proyecto.entrevistas.service.ResultadoPruebaService;
import com.proyecto.entrevistas.service.ResultadoPruebaService;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

@WebMvcTest(ResultadosPruebaController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ResultadosPruebaApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ResultadoPruebaService resultadoPruebaService;
    private ResultadoPruebaRepository resultadoPruebaRepository;
    ObjectMapper objectMapper;
    @BeforeEach
    void config() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName(value = "Test Controller - Create ResultadoPrueba")
    @Order(1)
    void saveResultadoPrueba() throws Exception {
        ResultadoPrueba resultadoPrueba = new ResultadoPrueba(1L,2L, new Date());
        
        when(resultadoPruebaService.save(any())).then(invocation -> {
            ResultadoPrueba u = invocation.getArgument(0);
            u.setId(5L);
            return u;
        });

        mockMvc.perform(post("/resultado-pruebas/").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(resultadoPrueba)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.idAgendaPrueba").value(2L));

        verify(resultadoPruebaService).save(any());
    }

    @Test
    @DisplayName(value = "Test Controller - Get all ResultadoPrueba")
    @Order(2)
    void getAllResultadoPrueba() throws Exception {
        mockMvc.perform(get("/resultado-pruebas/").header("Authorization","Bearer token-string"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Test Controller - Get id ResultadoPrueba")
    @Order(3)
    void getIdResultadoPrueba() throws Exception {
        ResultadoPrueba resultadoPrueba = new ResultadoPrueba(5L,1L,new Date());

        mockMvc.perform(get("/resultado-pruebas/5").header("Authorization","Bearer token-string"))
                .andExpect(status().isOk());
    }

}
