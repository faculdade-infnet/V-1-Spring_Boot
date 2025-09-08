package com.infnet.TP3.service;

import com.infnet.TP3.Entity.Funcionario;
import com.infnet.TP3.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository repository;

    // Rota GET All
    public List<Funcionario> getAll() {
        return repository.findAll();
    }

    // Rota GET by ID
    public Funcionario getId(Long id) {
        Optional<Funcionario> obj = repository.findById(id);
        return obj.get();
    }

    // Rota POST
    public Funcionario create(Funcionario obj) {
        return repository.save(obj);
    }

    // Rota DELETE
    public void delete(Long id) {
        repository.deleteById(id);
    }

    // Rota PUT
    public Funcionario update(Funcionario obj) {
        Optional<Funcionario> existingObj = repository.findById(obj.getId());
        if (existingObj.isPresent()) {
            updateFuncionario(existingObj.get(), obj);
            return repository.save(existingObj.get());
        } else {
            throw new RuntimeException("Funcionario não encontrado com ID: " + obj.getId());
        }
    }

    private void updateFuncionario(Funcionario existingObj, Funcionario obj) {
        existingObj.setNome(obj.getNome());
        existingObj.setCargo(obj.getCargo());
        existingObj.setSalario(obj.getSalario());
    }
}