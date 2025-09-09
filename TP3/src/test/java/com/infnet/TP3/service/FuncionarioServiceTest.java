package com.infnet.TP3.service;

import com.infnet.TP3.Entity.Funcionario;
import com.infnet.TP3.repository.FuncionarioRepository;
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

class FuncionarioServiceTest {
    @Mock
    private FuncionarioRepository repository;

    @InjectMocks
    private FuncionarioService service;

    private Funcionario funcionario1;
    private Funcionario funcionario2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        funcionario1 = new Funcionario(1L, "Marcos Paulo", "Analista de Sistemas", BigDecimal.valueOf(4500.00));
        funcionario2 = new Funcionario(2L, "Fernanda Costa", "Gerente de Projetos", BigDecimal.valueOf(7500.00));

    }

    @Test
    void testCreateFuncionario() {
        when(repository.save(funcionario1)).thenReturn(funcionario1);

        Funcionario resultado = service.create(funcionario1);
        assertNotNull(resultado);
        assertEquals("Marcos Paulo", resultado.getNome());

        verify(repository, times(1)).save(funcionario1);
    }

    @Test
    void testGetAllFuncionarios() {
        when(repository.findAll()).thenReturn(Arrays.asList(funcionario1, funcionario2));

        List<Funcionario> funcionarios = service.getAll();
        assertEquals(2, funcionarios.size());

        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetFuncionarioById() {
        when(repository.findById(1L)).thenReturn(Optional.of(funcionario1));

        Funcionario resultado = service.getId(1L);

        assertNotNull(resultado);
        assertEquals("Marcos Paulo", resultado.getNome());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testUpdateFuncionario() {
        Funcionario funcionarioAtualizado = new Funcionario(1L, "Funcionario Atualizado", "Analista de Sistemas", BigDecimal.valueOf(4500.00));

        when(repository.findById(1L)).thenReturn(Optional.of(funcionario1));
        when(repository.save(any(Funcionario.class))).thenReturn(funcionarioAtualizado);

        Funcionario resultado = service.update(funcionarioAtualizado);

        assertNotNull(resultado);
        assertEquals("Funcionario Atualizado", resultado.getNome());

        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(any(Funcionario.class));
    }

    @Test
    void testDeleteFuncionario() {
        doNothing().when(repository).deleteById(1L);
        service.delete(1L);

        verify(repository, times(1)).deleteById(1L);
    }
}
