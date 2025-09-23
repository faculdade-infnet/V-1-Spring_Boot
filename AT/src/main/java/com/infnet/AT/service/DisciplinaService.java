package com.infnet.AT.service;

import com.infnet.AT.entity.*;
import com.infnet.AT.repository.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class DisciplinaService {
    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    public Disciplina create(Disciplina obj) {
        return disciplinaRepository.save(obj);
    }

    public List<Disciplina> getAll() {
        return disciplinaRepository.findAll();
    }

    // Rota DELETE
    public void delete(Long id) {
        disciplinaRepository.deleteById(id);
    }

    public void alocarAluno(Long disciplinaId, Long alunoId) {
        Disciplina disciplina = disciplinaRepository.findById(disciplinaId)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));

        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        disciplina.adicionarAluno(aluno); // método da entidade Disciplina
        disciplinaRepository.save(disciplina);
    }

    public void atribuirNotaAluno(Long disciplinaId, Long alunoId, Double nota) {
        Disciplina disciplina = disciplinaRepository.findById(disciplinaId).orElseThrow();
        Aluno aluno = disciplina.getAlunos().stream()
                .filter(a -> a.getId().equals(alunoId))
                .findFirst()
                .orElseThrow();
        disciplina.getNotas().put(aluno, nota);
        disciplinaRepository.save(disciplina);
    }

    public List<Aluno> listarAlunosAprovados(Long disciplinaId) {
        Disciplina disciplina = disciplinaRepository.findById(disciplinaId).orElseThrow();
        return disciplina.getNotas().entrySet().stream()
                .filter(e -> e.getValue() >= 7)
                .map(Map.Entry::getKey)
                .toList();
    }

    public List<Aluno> listarAlunosReprovados(Long disciplinaId) {
        Disciplina disciplina = disciplinaRepository.findById(disciplinaId).orElseThrow();
        return disciplina.getNotas().entrySet().stream()
                .filter(e -> e.getValue() < 7)
                .map(Map.Entry::getKey)
                .toList();
    }
}

