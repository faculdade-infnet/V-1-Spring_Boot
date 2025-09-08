package com.infnet.TP3.controller;

import com.infnet.TP3.Entity.Cliente;
import com.infnet.TP3.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService service;

    // GET - Obtém todos os Clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    // GET - Obtém um Cliente através do id
    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> getId(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getId(id));
    }

    // POST - Criação de Cliente
    @PostMapping
    public ResponseEntity<Cliente> create(@RequestBody Cliente obj) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(obj));
    }

    // DELETE - Deleta um Cliente através do id
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // PUT - Atualiza um Cliente através do id
    @PutMapping(value = "/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente obj) {
        obj.setId(id);
        return ResponseEntity.ok().body(service.update(obj));
    }
}
