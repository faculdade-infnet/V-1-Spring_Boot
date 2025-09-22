package com.infnet.AT.service;

import com.infnet.AT.entity.Aluno;
import com.infnet.AT.entity.Disciplina;
import com.infnet.AT.repository.AlunoRepository;
import com.infnet.AT.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private DisciplinaRepository disciplinaRepository;

    // Rota GET All
    public List<Aluno> getAll() {
        return alunoRepository.findAll();
    }

    public Optional<Aluno> findById(Long id) {
        return alunoRepository.findById(id);
    }

    // Rota DELETE
    public void delete(Long id) {
        alunoRepository.deleteById(id);
    }

    // Rota POST - Cria aluno sem disciplinhas
    public Aluno create(Aluno obj) {
        return alunoRepository.save(obj);
    }

    public Map<String, Double> getNotasPorAluno(Long alunoId) {
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        Map<String, Double> resultado = new HashMap<>();
        for (Disciplina d : aluno.getDisciplinas()) {
            Double nota = d.getNotas().get(aluno); // pega a nota do aluno nessa disciplina
            if (nota != null) {
                resultado.put(d.getNome(), nota);
            }
        }
        return resultado;
    }
}