package com.infnet.AT.service;

import com.infnet.AT.entity.Aluno;
import com.infnet.AT.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository repository;

    // Rota GET All
    public List<Aluno> getAll() {
        return repository.findAll();
    }

    public Optional<Aluno> findById(Long id) {
        return repository.findById(id);
    }

    // Rota POST
    public Aluno create(Aluno obj) {
        return repository.save(obj);
    }
}