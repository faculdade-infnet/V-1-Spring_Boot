package com.infnet.TP3.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infnet.TP3.Entity.Cliente;
import com.infnet.TP3.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    private Cliente cliente1;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        cliente1 = new Cliente(1L, "João da Silva", "joao.silva@email.com", "(11) 99999-1111");
        objectMapper = new ObjectMapper();
    }

    @Test
    void testCreateCliente() throws Exception {
        when(clienteService.create(any(Cliente.class))).thenReturn(cliente1);

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("João da Silva"));

        verify(clienteService, times(1)).create(any(Cliente.class));
    }

    @Test
    void testGetAllClientes() throws Exception {
        when(clienteService.getAll()).thenReturn(Arrays.asList(cliente1));

        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        verify(clienteService, times(1)).getAll();
    }

    @Test
    void testGetClienteById() throws Exception {
        when(clienteService.getId(1L)).thenReturn(cliente1);

        mockMvc.perform(get("/clientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("João da Silva"));

        verify(clienteService, times(1)).getId(1L);
    }

    @Test
    void testDeleteCliente() throws Exception {
        doNothing().when(clienteService).delete(1L);

        mockMvc.perform(delete("/clientes/1"))
                .andExpect(status().isNoContent());

        verify(clienteService, times(1)).delete(1L);
    }

    @Test
    void testUpdateCliente() throws Exception {
        Cliente clienteAtualizado = new Cliente(1L, "Cliente Atualizado", "carlos.silva@email.com", "(11) 99999-000");
        when(clienteService.update(any(Cliente.class))).thenReturn(clienteAtualizado);

        mockMvc.perform(put("/clientes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteAtualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Cliente Atualizado"));

        verify(clienteService, times(1)).update(any(Cliente.class));
    }
}