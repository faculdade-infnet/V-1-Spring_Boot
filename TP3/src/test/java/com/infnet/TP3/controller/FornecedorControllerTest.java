package com.infnet.TP3.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infnet.TP3.Entity.Fornecedor;
import com.infnet.TP3.service.FornecedorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FornecedorController.class)
class FornecedorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FornecedorService service;

    private Fornecedor fornecedor1;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        fornecedor1 = new Fornecedor(1L, "Alpha Suprimentos Ltda", "12.345.678/0001-90", "(11) 4002-8922");
        objectMapper = new ObjectMapper();
    }

    @Test
    void testCreateFornecedor() throws Exception {
        when(service.create(any(Fornecedor.class))).thenReturn(fornecedor1);

        mockMvc.perform(post("/fornecedores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(fornecedor1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Alpha Suprimentos Ltda"));

        verify(service, times(1)).create(any(Fornecedor.class));
    }

    @Test
    void testGetAllFornecedors() throws Exception {
        when(service.getAll()).thenReturn(Arrays.asList(fornecedor1));

        mockMvc.perform(get("/fornecedores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        verify(service, times(1)).getAll();
    }

    @Test
    void testGetFornecedorById() throws Exception {
        when(service.getId(1L)).thenReturn(fornecedor1);

        mockMvc.perform(get("/fornecedores/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Alpha Suprimentos Ltda"));

        verify(service, times(1)).getId(1L);
    }

    @Test
    void testDeleteFornecedor() throws Exception {
        doNothing().when(service).delete(1L);

        mockMvc.perform(delete("/fornecedores/1"))
                .andExpect(status().isNoContent());

        verify(service, times(1)).delete(1L);
    }

    @Test
    void testUpdateFornecedor() throws Exception {
        Fornecedor fornecedorAtualizado = new Fornecedor(1L, "Fornecedor Atualizado",  "12.345.678/0001-90", "(11) 4002-8922");
        when(service.update(any(Fornecedor.class))).thenReturn(fornecedorAtualizado);

        mockMvc.perform(put("/fornecedores/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(fornecedorAtualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Fornecedor Atualizado"));

        verify(service, times(1)).update(any(Fornecedor.class));
    }
}