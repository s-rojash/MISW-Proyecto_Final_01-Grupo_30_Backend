package com.proyectofinalg30.candidatos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyectofinalg30.candidatos.controller.CandidatoController;
import com.proyectofinalg30.candidatos.model.Candidato;
import com.proyectofinalg30.candidatos.repository.CandidatoRepository;
import com.proyectofinalg30.candidatos.service.CandidatoService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CandidatoController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CandidatosApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CandidatoService candidatoService;
    private CandidatoRepository candidatoRepository;
    ObjectMapper objectMapper;
    @BeforeEach
    void config() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName(value = "Test Controller - Create candidatos")
    @Order(1)
    void saveCandidato() throws Exception {
        Candidato candidato = new Candidato("Steve","Rojas", "CC", 1D, "3100000000", "s.rojash@uniandes.edu.co","1234");
        when(candidatoService.save(any())).then(invocation -> {
            Candidato u = invocation.getArgument(0);
            u.setId(5L);
            return u;
        });

        mockMvc.perform(post("/candidatos/").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(candidato)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(5L))
                .andExpect(jsonPath("$.email").value("s.rojash@uniandes.edu.co"));

        verify(candidatoService).save(any());
    }

    @Test
    @DisplayName(value = "Test Controller - Auth candidato")
    @Order(2)
    void authCandidato() throws Exception {
        Candidato candidato = new Candidato("s.rojash@uniandes.edu.co","$2a$10$.j6vP9j/IpaHmBuwpOeGh.Veml0trzn5mEjsNa0d2s8l2KQNHVBSm");

        when(candidatoService.searchEmail(candidato.getEmail())).thenReturn(candidato);

        Candidato candidatoAuth = new Candidato("s.rojash@uniandes.edu.co","12345");

        mockMvc.perform(post("/candidatos/auth").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(candidatoAuth)))
                .andExpect(status().isOk());

        verify(candidatoService).searchEmail(candidato.getEmail());
    }

    @Test
    @DisplayName(value = "Test Controller - Auth not candidato")
    @Order(3)
    void authNotCandidato() throws Exception {
        Candidato candidato = new Candidato("s.rojash@uniandes.edu.co","$2a$10$QVm4xH5EWHWxiUFfr1Fki.z./KCkckUdn1Bn5ws.xWjRSebbVwKh6");

        when(candidatoService.searchEmail(candidato.getEmail())).thenReturn(candidato);

        Candidato candidatoAuth = new Candidato("s.rojash@uniandes.edu.co","12345");

        mockMvc.perform(post("/candidatos/auth").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(candidatoAuth)))
                .andExpect(status().isNotFound());

        verify(candidatoService).searchEmail(candidato.getEmail());
    }

    @Test
    @DisplayName(value = "Test Controller - Auth not exists email (email = null)")
    @Order(5)
    void authNotExistsEmailrNull() throws Exception {
        Candidato candidato = new Candidato("s.rojash@uniandes.edu.co","$2a$10$QVm4xH5EWHWxiUFfr1Fki.z./KCkckUdn1Bn5ws.xWjRSebbVwKh6");

        when(candidatoService.searchEmail(candidato.getEmail())).thenReturn(candidato);

        Candidato candidatoAuth = new Candidato(null,"1234");

        mockMvc.perform(post("/candidatos/auth").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(candidatoAuth)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName(value = "Test Controller - Auth not exists candidato (password = null)")
    @Order(7)
    void authNotExistsPasswordNull() throws Exception {
        Candidato candidato = new Candidato("s.rojash@uniandes.edu.co","$2a$10$QVm4xH5EWHWxiUFfr1Fki.z./KCkckUdn1Bn5ws.xWjRSebbVwKh6");

        when(candidatoService.searchEmail(candidato.getEmail())).thenReturn(candidato);

        Candidato candidatoAuth = new Candidato("s.rojash@uniandes.edu.co",null);

        mockMvc.perform(post("/candidatos/auth").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(candidatoAuth)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName(value = "Test Controller - Me token")
    @Order(8)
    void meToken() throws Exception {
        Candidato candidato = new Candidato(5L, "Steve","Rojas", "CC", 1D, "3100000000", "s.rojash@uniandes.edu.co","1234", "token-string", new Date(System.currentTimeMillis() + 1_800), new Date());

        when(candidatoService.searchToken(candidato.getToken())).thenReturn(candidato);

        mockMvc.perform(get("/candidatos/me").header("Authorization","Bearer token-string"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Test Controller - Get all candidatos")
    @Order(9)
    void getAllCandidatos() throws Exception {
        Candidato candidato = new Candidato(5L, "Steve","Rojas", "CC", 1D, "3100000000", "s.rojash@uniandes.edu.co","1234", "token-string", new Date(System.currentTimeMillis() + 1_800), new Date());

        when(candidatoService.searchToken(candidato.getToken())).thenReturn(candidato);

        mockMvc.perform(get("/candidatos/").header("Authorization","Bearer token-string"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Test Controller - Get id candidatos")
    @Order(10)
    void getIdCandidatos() throws Exception {
        Candidato candidato = new Candidato(5L, "Steve","Rojas", "CC", 1D, "3100000000", "s.rojash@uniandes.edu.co","1234", "token-string", new Date(System.currentTimeMillis() + 1_800), new Date());

        when(candidatoService.searchToken(candidato.getToken())).thenReturn(candidato);

        mockMvc.perform(get("/candidatos/5").header("Authorization","Bearer token-string"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Test Controller - Me unauthorized token")
    @Order(11)
    void meUnauthorizedToken() throws Exception {
        Candidato candidato = new Candidato(5L, "Steve","Rojas", "CC", 1D, "3100000000", "s.rojash@uniandes.edu.co","1234", "token-string", new Date(), new Date());

        when(candidatoService.searchToken(candidato.getToken())).thenReturn(candidato);

        mockMvc.perform(get("/candidatos/me").header("Authorization","Bearer token-string"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName(value = "Test Controller - Me Not Exists token")
    @Order(12)
    void meNotExistsToken() throws Exception {
        Candidato candidato = new Candidato(5L, "Steve","Rojas", "CC", 1D, "3100000000", "s.rojash@uniandes.edu.co","1234", "token-string", new Date(), new Date());

        when(candidatoService.searchToken(candidato.getToken())).thenReturn(candidato);

        mockMvc.perform(get("/candidatos/me").header("Authorization","token-string"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName(value = "Test Controller - Delete id candidatos")
    @Order(13)
    void getdeleteCandidatos() throws Exception {
        Candidato candidato = new Candidato(5L, "Steve","Rojas", "CC", 1D, "3100000000", "s.rojash@uniandes.edu.co","1234", "token-string", new Date(System.currentTimeMillis() + 1_800), new Date());

        when(candidatoService.searchToken(candidato.getToken())).thenReturn(candidato);

        mockMvc.perform(delete("/candidatos/5").header("Authorization","Bearer token-string"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Test Controller - Ping user")
    @Order(14)
    void testPing() throws Exception {
        mockMvc.perform(get("/candidatos/ping").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
