package com.infnet.TP3.service;

import com.infnet.TP3.Entity.Cliente;
import com.infnet.TP3.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ClienteServiceTest {
    @Mock
    private ClienteRepository repository;

    @InjectMocks
    private ClienteService service;

    private Cliente cliente1;
    private Cliente cliente2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cliente1 = new Cliente(1L, "João da Silva", "joao.silva@email.com", "(11) 99999-1111");
        cliente2 = new Cliente(2L, "Maria Oliveira", "maria.oliveira@email.com",                "(21) 98888-2222");
    }

    @Test
    void testCreateCliente() {
        when(repository.save(cliente1)).thenReturn(cliente1);

        Cliente resultado = service.create(cliente1);
        assertNotNull(resultado);
        assertEquals("João da Silva", resultado.getNome());

        verify(repository, times(1)).save(cliente1);
    }

    @Test
    void testGetAllClientes() {
        when(repository.findAll()).thenReturn(Arrays.asList(cliente1, cliente2));

        List<Cliente> clientes = service.getAll();
        assertEquals(2, clientes.size());

        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetClienteById() {
        when(repository.findById(1L)).thenReturn(Optional.of(cliente1));

        Cliente resultado = service.getId(1L);

        assertNotNull(resultado);
        assertEquals("João da Silva", resultado.getNome());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testUpdateCliente() {
        Cliente clienteAtualizado = new Cliente(1L, "Cliente Atualizado", "carlos.silva@email.com", "(11) 99999-000");

        when(repository.findById(1L)).thenReturn(Optional.of(cliente1));
        when(repository.save(any(Cliente.class))).thenReturn(clienteAtualizado);

        Cliente resultado = service.update(clienteAtualizado);

        assertNotNull(resultado);
        assertEquals("Cliente Atualizado", resultado.getNome());

        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(any(Cliente.class));
    }

    @Test
    void testDeleteCliente() {
        doNothing().when(repository).deleteById(1L);
        service.delete(1L);

        verify(repository, times(1)).deleteById(1L);
    }
}
