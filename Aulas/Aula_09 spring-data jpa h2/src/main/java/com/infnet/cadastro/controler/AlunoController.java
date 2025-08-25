package com.infnet.cadastro.controler;

import com.infnet.cadastro.entity.Aluno;
import com.infnet.cadastro.repository.AlunoRepository;
import com.infnet.cadastro.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/alunos")
public class AlunoController {
    @Autowired
    private AlunoService service;

    // POST - Criação de aluno
    @PostMapping
    public ResponseEntity<Aluno> create(@RequestBody Aluno obj) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(obj));
    }

    // DELETE - Deleta um aluno pelo seu id
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> create(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // GET - Obtém um Aluno cadastrado através do id
    @GetMapping(value = "/{id}")
    public ResponseEntity<Aluno> getId(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getId(id));
    }

    // GET - Obtém todos os alunos
    @GetMapping
    public ResponseEntity<List<Aluno>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    // PUT - Atualiza um aluno através do id
    @PutMapping(value = "/{id}")
    public ResponseEntity<Aluno> getId(@PathVariable Long id, @RequestBody Aluno obj) {
        obj.setId(id);
        return ResponseEntity.ok().body(service.update(obj));
    }
}
