package com.proyecto.entrevistas;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.entrevistas.controller.AgendaPruebaController;
import com.proyecto.entrevistas.model.AgendaPrueba;
import com.proyecto.entrevistas.repository.AgendaPruebaRepository;
import com.proyecto.entrevistas.service.AgendaPruebaService;

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

import java.util.Date;

@WebMvcTest(AgendaPruebaController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AgendaPruebaApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    private String token = "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2OTk4Mzk1ODcsIm5iZiI6MTY5OTgzOTU4NywiZXhwIjoxNzAxNjM5NTg3LCJpZEVtcHJlc2EiOiIxIn0.Q678kMC7lTpvME5jkpxVMFdhsJR7zIfUUPV6aTpyNK4";

    @MockBean
    private AgendaPruebaService agendaPruebaService;
    private AgendaPruebaRepository agendaPruebaRepository;
    ObjectMapper objectMapper;
    @BeforeEach
    void config() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName(value = "Test Controller - Create AgendaPrueba")
    @Order(1)
    void saveAgendaPrueba() throws Exception {
        AgendaPrueba agendaPrueba = new AgendaPrueba(0,1,2,3,new Date(), 5, "ok");
        
        when(agendaPruebaService.save(any())).then(invocation -> {
            AgendaPrueba u = invocation.getArgument(0);
            u.setId(5L);
            return u;
        });

        mockMvc.perform(post("/agenda-pruebas/")
        .header("Authorization","Bearer " + token)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(agendaPrueba)))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(5L))
        .andExpect(jsonPath("$.idPrueba").value(3));

        verify(agendaPruebaService).save(any());
    }

    @Test
    @DisplayName(value = "Test Controller - Get all AgendaPrueba")
    @Order(2)
    void getAllAgendaPrueba() throws Exception {
        mockMvc.perform(get("/agenda-pruebas/").header("Authorization","Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Test Controller - Get id AgendaPrueba")
    @Order(3)
    void getIdAgendaPrueba() throws Exception {
        AgendaPrueba agendaPrueba = new AgendaPrueba(5,1,2,3,new Date(System.currentTimeMillis()), 5, "ok");

        mockMvc.perform(get("/agenda-pruebas/5").header("Authorization","Bearer " + token))
                .andExpect(status().isOk());
    }

}
