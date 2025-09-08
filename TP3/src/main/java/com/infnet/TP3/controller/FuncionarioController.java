package com.infnet.TP3.controller;

import com.infnet.TP3.Entity.Funcionario;
import com.infnet.TP3.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fornecedores")
public class FuncionarioController {
    @Autowired
    private FuncionarioService service;

    // GET - Obtém todos os Funcionarios
    @GetMapping
    public ResponseEntity<List<Funcionario>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    // GET - Obtém um Funcionario através do id
    @GetMapping(value = "/{id}")
    public ResponseEntity<Funcionario> getId(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getId(id));
    }

    // POST - Criação de Funcionario
    @PostMapping
    public ResponseEntity<Funcionario> create(@RequestBody Funcionario obj) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(obj));
    }

    // DELETE - Deleta um Funcionario através do id
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> create(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // PUT - Atualiza um Funcionario através do id
    @PutMapping(value = "/{id}")
    public ResponseEntity<Funcionario> getId(@PathVariable Long id, @RequestBody Funcionario obj) {
        obj.setId(id);
        return ResponseEntity.ok().body(service.update(obj));
    }
}


