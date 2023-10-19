package com.proyectofinalg30.empresass.empresas;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyectofinalg30.empresass.empresas.controller.EmpresaController;
import com.proyectofinalg30.empresass.empresas.model.Empresa;
import com.proyectofinalg30.empresass.empresas.repository.EmpresaRepository;
import com.proyectofinalg30.empresass.empresas.service.EmpresaService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmpresaController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmpresasApplicationTests {

    Byte dijitoVerificacion = 1;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmpresaService empresaService;
    private EmpresaRepository empresaRepository;
    ObjectMapper objectMapper;
    @BeforeEach
    void config() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName(value = "Test Controller - Create empresa")
    @Order(1)
    void saveEmpresas() throws Exception {
        Empresa empresa = new Empresa("Empresa de prueba","NIT", 1D, dijitoVerificacion,"s.rojash@uniandes.edu.co","1234", new Date());
        when(empresaService.save(any())).then(invocation -> {
            Empresa u = invocation.getArgument(0);
            u.setId(5L);
            return u;
        });

        mockMvc.perform(post("/empresas/").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(empresa)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(5L))
                .andExpect(jsonPath("$.email").value("s.rojash@uniandes.edu.co"));

        verify(empresaService).save(any());
    }

    @Test
    @DisplayName(value = "Test Controller - Auth empresa")
    @Order(2)
    void authEmpresa() throws Exception {
        Empresa empresa = new Empresa("s.rojash@uniandes.edu.co","$2a$10$.j6vP9j/IpaHmBuwpOeGh.Veml0trzn5mEjsNa0d2s8l2KQNHVBSm");

        when(empresaService.searchEmail(empresa.getEmail())).thenReturn(empresa);

        Empresa empresaAuth = new Empresa("s.rojash@uniandes.edu.co","12345");

        mockMvc.perform(post("/empresas/auth").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(empresaAuth)))
                .andExpect(status().isOk());

        verify(empresaService).searchEmail(empresa.getEmail());
    }

    @Test
    @DisplayName(value = "Test Controller - Auth not empresa")
    @Order(3)
    void authNotEmpresa() throws Exception {
        Empresa empresa = new Empresa("s.rojash@uniandes.edu.co","$2a$10$QVm4xH5EWHWxiUFfr1Fki.z./KCkckUdn1Bn5ws.xWjRSebbVwKh6");

        when(empresaService.searchEmail(empresa.getEmail())).thenReturn(empresa);

        Empresa empresaAuth = new Empresa("s.rojash@uniandes.edu.co","12345");

        mockMvc.perform(post("/empresas/auth").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(empresaAuth)))
                .andExpect(status().isNotFound());

        verify(empresaService).searchEmail(empresa.getEmail());
    }

    @Test
    @DisplayName(value = "Test Controller - Auth not exists email (email = '')")
    @Order(4)
    void authNotExistsEmailVacio() throws Exception {
        Empresa empresa = new Empresa("s.rojash@uniandes.edu.co","$2a$10$QVm4xH5EWHWxiUFfr1Fki.z./KCkckUdn1Bn5ws.xWjRSebbVwKh6");

        when(empresaService.searchEmail(empresa.getEmail())).thenReturn(empresa);

        Empresa empresaAuth = new Empresa("","1234");

        mockMvc.perform(post("/empresas/auth").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(empresaAuth)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName(value = "Test Controller - Auth not exists email (email = null)")
    @Order(5)
    void authNotExistsEmailNull() throws Exception {
        Empresa empresa = new Empresa("s.rojash@uniandes.edu.co","$2a$10$QVm4xH5EWHWxiUFfr1Fki.z./KCkckUdn1Bn5ws.xWjRSebbVwKh6");

        when(empresaService.searchEmail(empresa.getEmail())).thenReturn(empresa);

        Empresa empresaAuth = new Empresa(null,"1234");

        mockMvc.perform(post("/empresas/auth").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(empresaAuth)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName(value = "Test Controller - Auth not exists empresa (password = '')")
    @Order(6)
    void authNotExistsPasswordVacio() throws Exception {
        Empresa empresa = new Empresa("s.rojash@uniandes.edu.co","$2a$10$QVm4xH5EWHWxiUFfr1Fki.z./KCkckUdn1Bn5ws.xWjRSebbVwKh6");

        when(empresaService.searchEmail(empresa.getEmail())).thenReturn(empresa);

        Empresa empresaAuth = new Empresa("s.rojash@uniandes.edu.co","");

        mockMvc.perform(post("/empresas/auth").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(empresaAuth)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName(value = "Test Controller - Auth not exists empresa (password = null)")
    @Order(7)
    void authNotExistsPasswordNull() throws Exception {
        Empresa empresa = new Empresa("s.rojash@uniandes.edu.co","$2a$10$QVm4xH5EWHWxiUFfr1Fki.z./KCkckUdn1Bn5ws.xWjRSebbVwKh6");

        when(empresaService.searchEmail(empresa.getEmail())).thenReturn(empresa);

        Empresa empresaAuth = new Empresa("s.rojash@uniandes.edu.co",null);

        mockMvc.perform(post("/empresas/auth").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(empresaAuth)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName(value = "Test Controller - Me token")
    @Order(8)
    void meToken() throws Exception {
        Empresa empresa = new Empresa(5L, "Empresa de prueba", "NIT", 1D, dijitoVerificacion, "s.rojash@uniandes.edu.co","1234", "token-string", new Date(System.currentTimeMillis() + 1_800), new Date());

        when(empresaService.searchToken(empresa.getToken())).thenReturn(empresa);

        mockMvc.perform(get("/empresas/me").header("Authorization","Bearer token-string"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Test Controller - Get all empresas")
    @Order(9)
    void getAllEmpresas() throws Exception {
        Empresa empresa = new Empresa(5L, "Empresa de prueba", "NIT", 1D, dijitoVerificacion, "s.rojash@uniandes.edu.co","1234", "token-string", new Date(System.currentTimeMillis() + 1_800), new Date());

        when(empresaService.searchToken(empresa.getToken())).thenReturn(empresa);

        mockMvc.perform(get("/empresas/").header("Authorization","Bearer token-string"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Test Controller - Get id empresa")
    @Order(10)
    void getIdEmpresa() throws Exception {
        Empresa candidato = new Empresa(5L, "Empresa de prueba", "NIT", 1D, dijitoVerificacion, "s.rojash@uniandes.edu.co","1234", "token-string", new Date(System.currentTimeMillis() + 1_800), new Date());

        when(empresaService.searchToken(candidato.getToken())).thenReturn(candidato);

        mockMvc.perform(get("/empresas/5").header("Authorization","Bearer token-string"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Test Controller - Me unauthorized token")
    @Order(11)
    void meUnauthorizedToken() throws Exception {
        Empresa empresa = new Empresa(5L, "Empresa de prueba", "NIT", 1D, dijitoVerificacion, "s.rojash@uniandes.edu.co","1234", "token-string", new Date(), new Date());

        when(empresaService.searchToken(empresa.getToken())).thenReturn(empresa);

        mockMvc.perform(get("/empresas/me").header("Authorization","Bearer token-string"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName(value = "Test Controller - Me Not Exists token")
    @Order(12)
    void meNotExistsToken() throws Exception {
        Empresa empresa = new Empresa(5L, "Empresa de prueba", "NIT", 1D, dijitoVerificacion, "s.rojash@uniandes.edu.co","1234", "token-string", new Date(), new Date());

        when(empresaService.searchToken(empresa.getToken())).thenReturn(empresa);

        mockMvc.perform(get("/empresas/me").header("Authorization","token-string"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName(value = "Test Controller - Delete id candidatos")
    @Order(13)
    void getdeleteEmpresa() throws Exception {
        Empresa empresa = new Empresa(5L, "Empresa de prueba", "NIT", 1D, dijitoVerificacion, "s.rojash@uniandes.edu.co","1234", "token-string", new Date(System.currentTimeMillis() + 1_800), new Date());

        when(empresaService.searchToken(empresa.getToken())).thenReturn(empresa);

        mockMvc.perform(delete("/empresas/5").header("Authorization","Bearer token-string"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Test Controller - Ping user")
    @Order(14)
    void testPing() throws Exception {
        mockMvc.perform(get("/empresas/ping").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
