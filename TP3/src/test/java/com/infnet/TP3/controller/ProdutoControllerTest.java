package com.infnet.TP3.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infnet.TP3.Entity.Produto;
import com.infnet.TP3.service.ProdutoService;
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

@WebMvcTest(ProdutoController.class)
class ProdutoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProdutoService service;

    private Produto produto1;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        produto1 = new Produto(1L, "Notebook X1", "Notebook com 16GB RAM e SSD 512GB",  BigDecimal.valueOf(4200.00));
        objectMapper = new ObjectMapper();
    }

    @Test
    void testCreateProduto() throws Exception {
        when(service.create(any(Produto.class))).thenReturn(produto1);

        mockMvc.perform(post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produto1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Notebook X1"));

        verify(service, times(1)).create(any(Produto.class));
    }

    @Test
    void testGetAllProdutos() throws Exception {
        when(service.getAll()).thenReturn(Arrays.asList(produto1));

        mockMvc.perform(get("/produtos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        verify(service, times(1)).getAll();
    }

    @Test
    void testGetProdutoById() throws Exception {
        when(service.getId(1L)).thenReturn(produto1);

        mockMvc.perform(get("/produtos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Notebook X1"));

        verify(service, times(1)).getId(1L);
    }

    @Test
    void testDeleteProduto() throws Exception {
        doNothing().when(service).delete(1L);

        mockMvc.perform(delete("/produtos/1"))
                .andExpect(status().isNoContent());

        verify(service, times(1)).delete(1L);
    }

    @Test
    void testUpdateProduto() throws Exception {
        Produto produtoAtualizado = new Produto(1L, "Produto Atualizado", "Notebook com 16GB RAM e SSD 512GB",  BigDecimal.valueOf(4200.00));
        when(service.update(any(Produto.class))).thenReturn(produtoAtualizado);

        mockMvc.perform(put("/produtos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produtoAtualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Produto Atualizado"));

        verify(service, times(1)).update(any(Produto.class));
    }
}