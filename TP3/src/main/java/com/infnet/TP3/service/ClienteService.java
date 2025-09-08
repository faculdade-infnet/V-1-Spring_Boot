package com.infnet.TP3.service;

import com.infnet.TP3.Entity.Cliente;
import com.infnet.TP3.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    // Rota GET All
    public List<Cliente> getAll() {
        return repository.findAll();
    }

    // Rota GET by ID
    public Cliente getId(Long id) {
        Optional<Cliente> obj = repository.findById(id);
        return obj.get();
    }

    // Rota POST
    public Cliente create(Cliente obj) {
        return repository.save(obj);
    }

    // Rota DELETE
    public void delete(Long id) {
        repository.deleteById(id);
    }

    // Rota PUT
    public Cliente update(Cliente obj) {
        Optional<Cliente> existingObj = repository.findById(obj.getId());
        if (existingObj.isPresent()) {
            updateCliente(existingObj.get(), obj);
            return repository.save(existingObj.get());
        } else {
            throw new RuntimeException("Cliente não encontrado com ID: " + obj.getId());
        }
    }

    private void updateCliente(Cliente existingObj, Cliente obj) {
        existingObj.setNome(obj.getNome());
        existingObj.setEmail(obj.getEmail());
        existingObj.setTelefone(obj.getTelefone());
    }
}