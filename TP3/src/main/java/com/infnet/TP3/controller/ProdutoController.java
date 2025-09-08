package com.infnet.TP3.controller;

import com.infnet.TP3.Entity.Produto;
import com.infnet.TP3.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService service;

    // GET - Obtém todos os Produtos
    @GetMapping
    public ResponseEntity<List<Produto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    // GET - Obtém um Produto através do id
    @GetMapping(value = "/{id}")
    public ResponseEntity<Produto> getId(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getId(id));
    }

    // POST - Criação de Produto
    @PostMapping
    public ResponseEntity<Produto> create(@RequestBody Produto obj) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(obj));
    }

    // DELETE - Deleta um Produto através do id
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // PUT - Atualiza um Produto através do id
    @PutMapping(value = "/{id}")
    public ResponseEntity<Produto> update(@PathVariable Long id, @RequestBody Produto obj) {
        obj.setId(id);
        return ResponseEntity.ok().body(service.update(obj));
    }
}