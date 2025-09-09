package com.infnet.TP3.service;

import com.infnet.TP3.Entity.Produto;
import com.infnet.TP3.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProdutoServiceTest {
    @Mock
    private ProdutoRepository repository;

    @InjectMocks
    private ProdutoService service;

    private Produto produto1;
    private Produto produto2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        produto1 = new Produto(1L, "Notebook X1", "Notebook com 16GB RAM e SSD 512GB", BigDecimal.valueOf(4200.00));
        produto2 = new Produto(2L, "Mouse Gamer", "Mouse óptico 7200 DPI", BigDecimal.valueOf(150.00));
    }

    @Test
    void testCreateProduto() {
        when(repository.save(produto1)).thenReturn(produto1);

        Produto resultado = service.create(produto1);
        assertNotNull(resultado);
        assertEquals("Notebook X1", resultado.getNome());

        verify(repository, times(1)).save(produto1);
    }

    @Test
    void testGetAllProdutos() {
        when(repository.findAll()).thenReturn(Arrays.asList(produto1, produto2));

        List<Produto> produtos = service.getAll();
        assertEquals(2, produtos.size());

        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetProdutoById() {
        when(repository.findById(1L)).thenReturn(Optional.of(produto1));

        Produto resultado = service.getId(1L);

        assertNotNull(resultado);
        assertEquals("Notebook X1", resultado.getNome());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testUpdateProduto() {
        Produto produtoAtualizado = new Produto(1L, "Produto Atualizado", "Notebook com 16GB RAM e SSD 512GB", BigDecimal.valueOf(4200.00));

        when(repository.findById(1L)).thenReturn(Optional.of(produto1));
        when(repository.save(any(Produto.class))).thenReturn(produtoAtualizado);

        Produto resultado = service.update(produtoAtualizado);

        assertNotNull(resultado);
        assertEquals("Produto Atualizado", resultado.getNome());

        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(any(Produto.class));
    }

    @Test
    void testDeleteProduto() {
        doNothing().when(repository).deleteById(1L);
        service.delete(1L);

        verify(repository, times(1)).deleteById(1L);
    }
}
