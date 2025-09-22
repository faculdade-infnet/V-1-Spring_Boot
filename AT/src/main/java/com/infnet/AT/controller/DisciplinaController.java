package com.infnet.AT.controller;

import com.infnet.AT.entity.Disciplina;
import com.infnet.AT.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {
    @Autowired
    private DisciplinaService service;

    // GET - Obtém todas as Disciplinas
    @GetMapping()
    public ResponseEntity<List<Disciplina>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    // POST - Criação de Disciplinas
    @PostMapping()
    public ResponseEntity<Disciplina> create(@RequestBody Disciplina obj) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(obj));
    }
}
