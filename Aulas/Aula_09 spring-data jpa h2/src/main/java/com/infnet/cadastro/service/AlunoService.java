package com.infnet.cadastro.service;

import com.infnet.cadastro.entity.Aluno;
import com.infnet.cadastro.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository repository;

    // Rota POST
    public Aluno create(Aluno obj) {
        return repository.save(obj);
    }

    // Rota DELETE
    public void delete(Long id) {
        repository.deleteById(id);
    }

    // Rota GET by ID
    public Aluno getId(Long id) {
        Optional<Aluno> obj = repository.findById(id);
        return obj.get();
    }

    // Rota GET All
    public List<Aluno> getAll() {
        return repository.findAll();
    }

    // Rota PUT
    public Aluno update(Aluno obj) {
        Optional<Aluno> newObj = repository.findById(obj.getId());
        UpdateAluno(newObj, obj);
        return repository.save(newObj.get());
    }

    private void UpdateAluno(Optional<Aluno> newObj, Aluno obj) {
        newObj.get().setNome(obj.getNome());
    }
}
