package com.infnet.TP3.service;

import com.infnet.TP3.Entity.Produto;
import com.infnet.TP3.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;

    // Rota GET All
    public List<Produto> getAll() {
        return repository.findAll();
    }

    // Rota GET by ID
    public Produto getId(Long id) {
        Optional<Produto> obj = repository.findById(id);
        return obj.get();
    }

    // Rota POST
    public Produto create(Produto obj) {
        return repository.save(obj);
    }

    // Rota DELETE
    public void delete(Long id) {
        repository.deleteById(id);
    }

    // Rota PUT
    public Produto update(Produto obj) {
        Optional<Produto> existingObj = repository.findById(obj.getId());
        if (existingObj.isPresent()) {
            updateProduto(existingObj.get(), obj);
            return repository.save(existingObj.get());
        } else {
            throw new RuntimeException("Produto não encontrado com ID: " + obj.getId());
        }
    }

    private void updateProduto(Produto existingObj, Produto obj) {
        existingObj.setNome(obj.getNome());
        existingObj.setDescricao(obj.getDescricao());
        existingObj.setPreco(obj.getPreco());
    }
}
