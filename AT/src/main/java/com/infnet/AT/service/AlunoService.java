package com.infnet.AT.service;

import com.infnet.AT.entity.Aluno;
import com.infnet.AT.entity.Disciplina;
import com.infnet.AT.repository.AlunoRepository;
import com.infnet.AT.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
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
}