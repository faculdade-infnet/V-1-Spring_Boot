package com.infnet.AT.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infnet.AT.entity.*;
import com.infnet.AT.service.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import java.util.*;

@WebMvcTest(ProfessorController.class)
@AutoConfigureMockMvc(addFilters = false)
class ProfessorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlunoService alunoService;

    @MockBean
    private DisciplinaService disciplinaService;

    private Aluno aluno1;  // aprovado
    private Aluno aluno2;  // aprovado
    private Aluno alunoReprovado;  // reprovado
    private Disciplina disciplina1;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        // nome, cpf, email, teelfon, enderec, disciplinhas
        aluno1 = new Aluno(1L, "Lucas Silva", "123.456.789-01",
                "lucas.silva@email.com", "(11) 91234-5678",
                "Rua A, 123, São Paulo, SP", new HashSet<>());

        aluno2 = new Aluno(2L, "Mariana Oliveira", "987.654.321-00",
                "mariana.oliveira@email.com", "(21) 92345-6789",
                "Rua B, 456, Rio de Janeiro, RJ", new HashSet<>());

        // aluno reprovado
        alunoReprovado = new Aluno(3L, "Gabriel Santos", "345.678.901-23",
                "gabriel.santos@email.com", "(31) 93456-7890",
                "Av. C, 789, Belo Horizonte, MG", new HashSet<>());

        // nome, codigo, alunos, notas
        disciplina1 = new Disciplina(1L, "Matemática", "01",
                new HashSet<>(), new HashMap<>());

        objectMapper = new ObjectMapper();
    }

    @Test
    void testCreateAluno() throws Exception {
        when(alunoService.create(any(Aluno.class))).thenReturn(aluno1);

        mockMvc.perform(post("/professor/alunos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(aluno1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Lucas Silva"));

        verify(alunoService, times(1)).create(any(Aluno.class));
    }

    @Test
    void testGetAllAlunos() throws Exception {
        when(alunoService.getAll()).thenReturn(Arrays.asList(aluno1));

        mockMvc.perform(get("/professor/alunos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        verify(alunoService, times(1)).getAll();
    }

    @Test
    void testDeleteAluno() throws Exception {
        doNothing().when(alunoService).delete(1L);

        mockMvc.perform(delete("/professor/alunos/1"))
                .andExpect(status().isNoContent());

        verify(alunoService, times(1)).delete(1L);
    }

    @Test
    void testeCreateDisciplina() throws Exception {
        when(disciplinaService.create(any(Disciplina.class))).thenReturn(disciplina1);

        mockMvc.perform(post("/professor/disciplinas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(disciplina1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Matemática"));

        verify(disciplinaService, times(1)).create(any(Disciplina.class));
    }

    @Test
    void testGetAllDisciplinas() throws Exception {
        when(disciplinaService.getAll()).thenReturn(Arrays.asList(disciplina1));

        mockMvc.perform(get("/professor/disciplinas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        verify(disciplinaService, times(1)).getAll();
    }

    @Test
    void testDeleteDisciplina() throws Exception {
        doNothing().when(disciplinaService).delete(1L);

        mockMvc.perform(delete("/professor/disciplinas/1"))
                .andExpect(status().isNoContent());

        verify(disciplinaService, times(1)).delete(1L);
    }

    @Test
    void testAlocarAlunoEmDisciplina() throws Exception {
        Long disciplinaId = 1L;
        Long alunoId = 1L;

        mockMvc.perform(post("/professor/disciplinas/{disciplinaId}/alunos/{alunoId}", disciplinaId, alunoId))
                .andExpect(status().isOk());

        verify(disciplinaService, times(1)).alocarAluno(disciplinaId, alunoId);
    }

    @Test
    void testAtribuirNotaAluno() throws Exception {
        Long disciplinaId = 1L;
        Long alunoId = 1L;
        Double nota = 8.5;

        mockMvc.perform(post("/professor/disciplinas/{disciplinaId}/notas/{alunoId}", disciplinaId, alunoId)
                        .param("nota", String.valueOf(nota)))
                .andExpect(status().isOk());

        verify(disciplinaService, times(1))
                .atribuirNotaAluno(disciplinaId, alunoId, nota);
    }

    @Test
    void testAlunosAprovados() throws Exception {
        Long disciplinaId = 1L;
        List<Aluno> aprovados = List.of(aluno1, aluno2);

        when(disciplinaService.listarAlunosAprovados(disciplinaId))
                .thenReturn(aprovados);

        mockMvc.perform(get("/professor/disciplinas/{disciplinaId}/aprovados", disciplinaId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Lucas Silva"))
                .andExpect(jsonPath("$[1].nome").value("Mariana Oliveira"));

        verify(disciplinaService, times(1)).listarAlunosAprovados(disciplinaId);
    }

    @Test
    void testAlunosReprovados()  throws Exception {
        Long disciplinaId = 1L;

        when(disciplinaService.listarAlunosReprovados(disciplinaId))
                .thenReturn(List.of(alunoReprovado));

        mockMvc.perform(get("/professor/disciplinas/{disciplinaId}/reprovados", disciplinaId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(3L))
                .andExpect(jsonPath("$[0].nome").value("Gabriel Santos"));

        verify(disciplinaService, times(1)).listarAlunosReprovados(disciplinaId);
    }

    @Test
    void testGetNotas()  throws Exception {
        Long alunoId = 1L;

        // mapa de notas
        Map<String, Double> notas = new HashMap<>();
        notas.put("Matemática", 8.5);
        notas.put("Português", 7.0);

        when(alunoService.getNotasPorAluno(alunoId)).
                thenReturn(notas);

        mockMvc.perform(get("/professor/alunos/{alunoId}/notas", alunoId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Matemática").value(8.5))
                .andExpect(jsonPath("$.Português").value(7.0));

        verify(alunoService, times(1)).getNotasPorAluno(alunoId);
    }
}