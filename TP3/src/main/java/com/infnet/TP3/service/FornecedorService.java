package com.infnet.TP3.service;

import com.infnet.TP3.Entity.Fornecedor;
import com.infnet.TP3.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {
    @Autowired
    private FornecedorRepository repository;

    // Rota GET All
    public List<Fornecedor> getAll() {
        return repository.findAll();
    }

    // Rota GET by ID
    public Fornecedor getId(Long id) {
        Optional<Fornecedor> obj = repository.findById(id);
        return obj.get();
    }

    // Rota POST
    public Fornecedor create(Fornecedor obj) {
        return repository.save(obj);
    }

    // Rota DELETE
    public void delete(Long id) {
        repository.deleteById(id);
    }

    // Rota PUT
    public Fornecedor update(Fornecedor obj) {
        Optional<Fornecedor> existingObj = repository.findById(obj.getId());
        if (existingObj.isPresent()) {
            updateFornecedor(existingObj.get(), obj);
            return repository.save(existingObj.get());
        } else {
            throw new RuntimeException("Fornecedor não encontrado com ID: " + obj.getId());
        }
    }

    private void updateFornecedor(Fornecedor existingObj, Fornecedor obj) {
        existingObj.setNome(obj.getNome());
        existingObj.setCnpj(obj.getCnpj());
        existingObj.setTelefone(obj.getTelefone());
    }
}