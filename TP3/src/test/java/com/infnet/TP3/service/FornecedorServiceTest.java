package com.infnet.TP3.service;

import com.infnet.TP3.Entity.Fornecedor;
import com.infnet.TP3.repository.FornecedorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FornecedorServiceTest {
    @Mock
    private FornecedorRepository repository;

    @InjectMocks
    private FornecedorService service;

    private Fornecedor fornecedor1;
    private Fornecedor fornecedor2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        fornecedor1 = new Fornecedor(1L, "Alpha Suprimentos Ltda", "12.345.678/0001-90", "(11) 4002-8922");
        fornecedor2 = new Fornecedor(2L, "Beta Distribuidora SA", "98.765.432/0001-12", "(21) 3222-3344");
    }

    @Test
    void testCreateFornecedor() {
        when(repository.save(fornecedor1)).thenReturn(fornecedor1);

        Fornecedor resultado = service.create(fornecedor1);
        assertNotNull(resultado);
        assertEquals("Alpha Suprimentos Ltda", resultado.getNome());

        verify(repository, times(1)).save(fornecedor1);
    }

    @Test
    void testGetAllFornecedors() {
        when(repository.findAll()).thenReturn(Arrays.asList(fornecedor1, fornecedor2));

        List<Fornecedor> fornecedors = service.getAll();
        assertEquals(2, fornecedors.size());

        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetFornecedorById() {
        when(repository.findById(1L)).thenReturn(Optional.of(fornecedor1));

        Fornecedor resultado = service.getId(1L);

        assertNotNull(resultado);
        assertEquals("Alpha Suprimentos Ltda", resultado.getNome());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testUpdateFornecedor() {
        Fornecedor fornecedorAtualizado = new Fornecedor(1L, "Fornecedor Atualizado", "12.345.678/0001-90", "(11) 4002-8922");

        when(repository.findById(1L)).thenReturn(Optional.of(fornecedor1));
        when(repository.save(any(Fornecedor.class))).thenReturn(fornecedorAtualizado);

        Fornecedor resultado = service.update(fornecedorAtualizado);

        assertNotNull(resultado);
        assertEquals("Fornecedor Atualizado", resultado.getNome());

        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(any(Fornecedor.class));
    }

    @Test
    void testDeleteFornecedor() {
        doNothing().when(repository).deleteById(1L);
        service.delete(1L);

        verify(repository, times(1)).deleteById(1L);
    }
}
