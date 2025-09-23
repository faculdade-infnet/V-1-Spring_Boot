package com.infnet.AT.service;

import com.infnet.AT.entity.Aluno;
import com.infnet.AT.entity.Disciplina;
import com.infnet.AT.repository.AlunoRepository;
import com.infnet.AT.repository.DisciplinaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DisciplinaServiceTest {
    @Mock
    private DisciplinaRepository disciplinaRepository;

    @Mock
    private AlunoRepository alunoRepository;

    @InjectMocks
    private DisciplinaService disciplinaService;

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
    void testCreateDisciplina() {
        when(disciplinaRepository.save(disciplina1)).thenReturn(disciplina1);

        Disciplina resultado = disciplinaService.create(disciplina1);
        assertNotNull(resultado);
        assertEquals("Matemática", resultado.getNome());

        verify(disciplinaRepository, times(1)).save(disciplina1);
    }

    @Test
    void testGetAllDisciplina() {
        when(disciplinaRepository.findAll()).thenReturn(Arrays.asList(disciplina1, disciplina2));

        List<Disciplina> disciplinas = disciplinaService.getAll();
        assertEquals(2, disciplinas.size());

        verify(disciplinaRepository, times(1)).findAll();
    }

    @Test
    void testDeleteDisciplina() {
        doNothing().when(disciplinaRepository).deleteById(1L);
        disciplinaService.delete(1L);

        verify(disciplinaRepository, times(1)).deleteById(1L);
    }

    @Test
    void testAlocarAluno() {
        Long disciplinaId = 1L;
        Long alunoId = 1L;

        // busca disciplina e aluno pelo ID e retrona o objeto criado
        // salva a ddisciplina no repository
        when(disciplinaRepository.findById(disciplinaId)).thenReturn(Optional.of(disciplina1));
        when(alunoRepository.findById(alunoId)).thenReturn(Optional.of(aluno1));
        when(disciplinaRepository.save(disciplina1)).thenReturn(disciplina1);

        // faz alocação de aluno
        disciplinaService.alocarAluno(disciplinaId, alunoId);

        // Verifica se a disciplina tem esse aluno na lista.
        // Verifica se o aluno também tem a disciplina na sua lista.
        assertTrue(disciplina1.getAlunos().contains(aluno1));
        assertTrue(aluno1.getDisciplinas().contains(disciplina1));

        // Verifica se o service de fato buscou a disciplina no repo.
        // Verifica se o service buscou o aluno no repo.
        // Verifica se o service salvou a disciplina depois de adicionar o aluno.
        verify(disciplinaRepository, times(1)).findById(disciplinaId);
        verify(alunoRepository, times(1)).findById(alunoId);
        verify(disciplinaRepository, times(1)).save(disciplina1);
    }

    @Test
    void testAtribuirNotaAluno() {
        Long disciplinaId = 1L;
        Long alunoId = 1L;
        Double nota = 8.5;

        // garante que exista um aluno vinculado a disciplina
        disciplina1.adicionarAluno(aluno1);
        when(disciplinaRepository.findById(disciplinaId)).thenReturn(Optional.of(disciplina1));
        when(disciplinaRepository.save(disciplina1)).thenReturn(disciplina1);

        disciplinaService.atribuirNotaAluno(disciplinaId, alunoId, nota);

        // Verifica se a nota foi atribuída
        assertEquals(nota, disciplina1.getNotas().get(aluno1));
        verify(disciplinaRepository, times(1)).save(disciplina1);
    }

    @Test
    void testListarAlunosAprovados() {
        Long disciplinaId = 1L;

        // Adiciona aluno a disciplina e notas
        disciplina1.adicionarAluno(aluno1);
        disciplina1.adicionarAluno(aluno2);
        disciplina1.getNotas().put(aluno1, 8.5); // aprovado
        disciplina1.getNotas().put(aluno2, 6.0); // reprovado

        // Configuração de dados mock
        when(disciplinaRepository.findById(disciplinaId)).thenReturn(Optional.of(disciplina1));

        // Execução de Teste
        List<Aluno> aprovados = disciplinaService.listarAlunosAprovados(disciplinaId);

        // Verifica se a lista de aprovados tem apenas 1 item
        assertEquals(1, aprovados.size());
        // Verifica se a lista contem os dois objetos aluno1 e aluno2
        assertTrue(aprovados.contains(aluno1));
        assertFalse(aprovados.contains(aluno2));

        verify(disciplinaRepository, times(1)).findById(disciplinaId);
    }

    @Test
    void testListarAlunosReprovados() {
        Long disciplinaId = 1L;

        // Adiciona aluno a disciplina e notas
        disciplina1.adicionarAluno(aluno1);
        disciplina1.adicionarAluno(aluno2);
        disciplina1.getNotas().put(aluno1, 8.5); // aprovado
        disciplina1.getNotas().put(aluno2, 6.0); // reprovado

        // Configuração de dados mock
        when(disciplinaRepository.findById(disciplinaId)).thenReturn(Optional.of(disciplina1));

        // Execução de Teste
        List<Aluno> reprovados = disciplinaService.listarAlunosReprovados(disciplinaId);

        // Verifica se a lista de reprovados tem apenas 1 item
        assertEquals(1, reprovados.size());
        // Verifica se a lista contem os dois objetos aluno1 e aluno2
        assertTrue(reprovados.contains(aluno2));
        assertFalse(reprovados.contains(aluno1));

        verify(disciplinaRepository, times(1)).findById(disciplinaId);
    }
}