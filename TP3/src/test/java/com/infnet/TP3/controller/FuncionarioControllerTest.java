package com.infnet.TP3.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infnet.TP3.Entity.Funcionario;
import com.infnet.TP3.service.FuncionarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FuncionarioController.class)
class FuncionarioControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FuncionarioService service;

    private Funcionario funcionario1;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        funcionario1 = new Funcionario(1L, "Marcos Paulo", "Analista de Sistemas", BigDecimal.valueOf(4500.00));
        objectMapper = new ObjectMapper();
    }

    @Test
    void testCreateFuncionario() throws Exception {
        when(service.create(any(Funcionario.class))).thenReturn(funcionario1);

        mockMvc.perform(post("/funcionarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(funcionario1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Marcos Paulo"));

        verify(service, times(1)).create(any(Funcionario.class));
    }

    @Test
    void testGetAllFuncionarios() throws Exception {
        when(service.getAll()).thenReturn(Arrays.asList(funcionario1));

        mockMvc.perform(get("/funcionarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        verify(service, times(1)).getAll();
    }

    @Test
    void testGetFuncionarioById() throws Exception {
        when(service.getId(1L)).thenReturn(funcionario1);

        mockMvc.perform(get("/funcionarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Marcos Paulo"));

        verify(service, times(1)).getId(1L);
    }

    @Test
    void testDeleteFuncionario() throws Exception {
        doNothing().when(service).delete(1L);

        mockMvc.perform(delete("/funcionarios/1"))
                .andExpect(status().isNoContent());

        verify(service, times(1)).delete(1L);
    }

    @Test
    void testUpdateFuncionario() throws Exception {
        Funcionario funcionarioAtualizado = new Funcionario(1L, "Funcionario Atualizado", "Analista de Sistemas", BigDecimal.valueOf(4500.00));
        when(service.update(any(Funcionario.class))).thenReturn(funcionarioAtualizado);

        mockMvc.perform(put("/funcionarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(funcionarioAtualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Funcionario Atualizado"));

        verify(service, times(1)).update(any(Funcionario.class));
    }
}