package com.infnet.AT.controller;

import com.infnet.AT.entity.Aluno;
import com.infnet.AT.entity.Disciplina;
import com.infnet.AT.service.AlunoService;
import com.infnet.AT.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private AlunoService alunoService;
    @Autowired
    private DisciplinaService disciplinaService;

    // GET - Obtém todos os Alunos
    @GetMapping("/alunos")
    public ResponseEntity<List<Aluno>> getAllAlunos() {
        return ResponseEntity.ok().body(alunoService.getAll());
    }

    // POST - Criação de Aluno
    @PostMapping("/alunos")
    public ResponseEntity<Aluno> createAluno(@RequestBody Aluno obj) {
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoService.create(obj));
    }

    // DELETE - Deleta um Aluno através do id
    @DeleteMapping(value = "/alunos/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        alunoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // GET - Obtém todos os Alunos
    @GetMapping("/disciplinas")
    public ResponseEntity<List<Disciplina>> getAllDisciplinas() {
        return ResponseEntity.ok().body(disciplinaService.getAll());
    }

    // POST - Criação de Disciplina
    @PostMapping("/disciplinas")
    public  ResponseEntity<Disciplina> cadastrarDisciplina(@RequestBody Disciplina obj) {
        return ResponseEntity.status(HttpStatus.CREATED).body(disciplinaService.create(obj));
    }

    // buscar aluno e alocar
    @PostMapping("/disciplinas/{disciplinaId}/alunos/{alunoId}")
    public void alocarAluno(@PathVariable Long disciplinaId, @PathVariable Long alunoId) {
        disciplinaService.alocarAluno(disciplinaId, alunoId);
    }

    // Adiciona Nota a um aluno
    // http://localhost:8080/professor/disciplinas/1/notas/1?nota=8.5
    @PostMapping("/disciplinas/{disciplinaId}/notas/{alunoId}")
    public void atribuirNotaAluno(@PathVariable Long disciplinaId,
                                  @PathVariable Long alunoId,
                                  @RequestParam Double nota) {
        disciplinaService.atribuirNotaAluno(disciplinaId, alunoId, nota);
    }

    // Obtém todos os alunos aprovados
    // http://localhost:8080/professor/disciplinas/1/aprovados
    @GetMapping("/disciplinas/{disciplinaId}/aprovados")
    public List<Aluno> alunosAprovados(@PathVariable Long disciplinaId) {
        return disciplinaService.listarAlunosAprovados(disciplinaId);
    }

    // Obtém todos os alunos reprovados
    // http://localhost:8080/professor/disciplinas/1/reprovados
    @GetMapping("/disciplinas/{disciplinaId}/reprovados")
    public List<Aluno> alunosReprovados(@PathVariable Long disciplinaId) {
        return disciplinaService.listarAlunosReprovados(disciplinaId);
    }

    // Obtem as notas de uma id de aluno
    // http://localhost:8080/professor/alunos/1/notas
    @GetMapping("/alunos/{alunoId}/notas")
    public ResponseEntity<Map<String, Double>> getNotas(@PathVariable Long alunoId) {
        Map<String, Double> notas = alunoService.getNotasPorAluno(alunoId);
        return ResponseEntity.ok(notas);
    }
}
