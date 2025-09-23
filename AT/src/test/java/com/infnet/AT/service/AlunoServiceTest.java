package com.infnet.AT.service;

import com.infnet.AT.entity.Aluno;
import com.infnet.AT.entity.Disciplina;
import com.infnet.AT.repository.AlunoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class AlunoServiceTest {
    @Mock
    private AlunoRepository alunoRepository;

    @InjectMocks
    private AlunoService alunoService;

    private Aluno aluno1;
    private Aluno aluno2;
    private Disciplina disciplina1;
    private Disciplina disciplina2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Cria alunos
        // nome, cpf, email, teelfon, enderec, disciplinhas
        aluno1 = new Aluno(1L, "Lucas Silva", "123.456.789-01",
                "lucas.silva@email.com", "(11) 91234-5678",
                "Rua A, 123, São Paulo, SP", new HashSet<>());

        aluno2 = new Aluno(2L, "Mariana Oliveira", "987.654.321-00",
                "mariana.oliveira@email.com", "(21) 92345-6789",
                "Rua B, 456, Rio de Janeiro, RJ", new HashSet<>());

        // Cria Disciplinas
        // id, nome, cdoigo, alunos, notas,
        disciplina1 = new Disciplina(1L, "Matemática", "01", new HashSet<>(), new HashMap<>());
        disciplina2 = new Disciplina(2L, "Português", "02", new HashSet<>(), new HashMap<>());
    }

    @Test
    void testCreateAluno() {
        when(alunoRepository.save(aluno1)).thenReturn(aluno1);

        Aluno resultado = alunoService.create(aluno1);
        assertNotNull(resultado);
        assertEquals("Lucas Silva", resultado.getNome());

        verify(alunoRepository, times(1)).save(aluno1);
    }

    @Test
    void testGetAllAluno() {
        when(alunoRepository.findAll()).thenReturn(Arrays.asList(aluno1, aluno2));

        List<Aluno> alunos = alunoService.getAll();
        assertEquals(2, alunos.size());

        verify(alunoRepository, times(1)).findAll();
    }

    @Test
    void testDeleteAluno() {
        doNothing().when(alunoRepository).deleteById(1L);
        alunoService.delete(1L);

        verify(alunoRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetNotasPorAluno() {
        // vincula aluno às disciplinas
        aluno1.getDisciplinas().add(disciplina1);
        aluno1.getDisciplinas().add(disciplina2);
        disciplina1.getAlunos().add(aluno1);
        disciplina2.getAlunos().add(aluno1);

        // adiciona notas
        disciplina1.getNotas().put(aluno1, 8.5);
        disciplina2.getNotas().put(aluno1, 6.0);

        // mocka o repository
        when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno1));

        // executa o service
        Map<String, Double> notas = alunoService.getNotasPorAluno(1L);

        // valida
        assertEquals(2, notas.size());
        assertEquals(8.5, notas.get("Matemática"));
        assertEquals(6.0, notas.get("Português"));

        verify(alunoRepository, times(1)).findById(1L);
    }
}