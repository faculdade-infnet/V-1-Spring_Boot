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

    @PostMapping("/disciplinas/{disciplinaId}/notas/{alunoId}")
    public void atribuirNota(@PathVariable Long disciplinaId,
                             @PathVariable Long alunoId,
                             @RequestParam Double nota) {
        disciplinaService.atribuirNota(disciplinaId, alunoId, nota);
    }

    @GetMapping("/disciplinas/{disciplinaId}/aprovados")
    public List<Aluno> alunosAprovados(@PathVariable Long disciplinaId) {
        return disciplinaService.listarAprovados(disciplinaId);
    }

    @GetMapping("/disciplinas/{disciplinaId}/reprovados")
    public List<Aluno> alunosReprovados(@PathVariable Long disciplinaId) {
        return disciplinaService.listarReprovados(disciplinaId);
    }
}
