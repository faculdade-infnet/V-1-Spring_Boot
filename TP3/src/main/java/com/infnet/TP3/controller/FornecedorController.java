package com.infnet.TP3.controller;

import com.infnet.TP3.Entity.Fornecedor;
import com.infnet.TP3.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {
    @Autowired
    private FornecedorService service;

    // GET - Obtém um Fornecedor através do id
    @GetMapping(value = "/{id}")
    public ResponseEntity<Fornecedor> getId(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getId(id));
    }

    // GET - Obtém todos os Fornecedors
    @GetMapping
    public ResponseEntity<List<Fornecedor>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    // POST - Criação de Fornecedor
    @PostMapping
    public ResponseEntity<Fornecedor> create(@RequestBody Fornecedor obj) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(obj));
    }

    // DELETE - Deleta um Fornecedor através do id
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> create(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // PUT - Atualiza um Fornecedor através do id
    @PutMapping(value = "/{id}")
    public ResponseEntity<Fornecedor> getId(@PathVariable Long id, @RequestBody Fornecedor obj) {
        obj.setId(id);
        return ResponseEntity.ok().body(service.update(obj));
    }
}
