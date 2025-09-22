package com.infnet.AT.controller;

import com.infnet.AT.entity.Aluno;
import com.infnet.AT.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
    @Autowired
    private AlunoService service;

    // GET - Obtém todos os Alunos
    @GetMapping
    public ResponseEntity<List<Aluno>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    // POST - Criação de Aluno
    @PostMapping
    public ResponseEntity<Aluno> create(@RequestBody Aluno obj) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(obj));
    }
}
