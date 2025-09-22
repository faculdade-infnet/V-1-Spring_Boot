package com.infnet.AT.controller;

import com.infnet.AT.entity.*;
import com.infnet.AT.service.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private AlunoService alunoService;
    @Autowired
    private DisciplinaService disciplinaService;

    // POST - Criação de Aluno
    // http://localhost:8080/professor/alunos
    @PostMapping("/alunos")
    public ResponseEntity<Aluno> createAluno(@RequestBody Aluno obj) {
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoService.create(obj));
    }

    // GET - Obtém todos os Alunos
    // http://localhost:8080/professor/alunos
    @GetMapping("/alunos")
    public ResponseEntity<List<Aluno>> getAllAlunos() {
        return ResponseEntity.ok().body(alunoService.getAll());
    }

    // DELETE - Deleta um Aluno através do id
    @DeleteMapping(value = "/alunos/{id}")
    public ResponseEntity<Void> deleteAluno(@PathVariable Long id) {
        alunoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // POST - Criação de Disciplina
    // http://localhost:8080/professor/disciplinas
    @PostMapping("/disciplinas")
    public  ResponseEntity<Disciplina> createDisciplina(@RequestBody Disciplina obj) {
        return ResponseEntity.status(HttpStatus.CREATED).body(disciplinaService.create(obj));
    }

    // GET - Obtém todos os Disciplinas
    // http://localhost:8080/professor/disciplinas
    @GetMapping("/disciplinas")
    public ResponseEntity<List<Disciplina>> getAllDisciplinas() {
        return ResponseEntity.ok().body(disciplinaService.getAll());
    }

    // DELETE - Deleta um Aluno através do id
    @DeleteMapping(value = "/disciplinas/{id}")
    public ResponseEntity<Void> deleteDisciplina(@PathVariable Long id) {
        disciplinaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Buscar aluno e alocar em disciplinha
    // http://localhost:8080/professor/disciplinas/3/alunos/2
    @PostMapping("/disciplinas/{disciplinaId}/alunos/{alunoId}")
    public void alocarAlunoEmDisciplina(@PathVariable Long disciplinaId, @PathVariable Long alunoId) {
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
