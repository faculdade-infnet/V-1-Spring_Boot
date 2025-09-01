package com.infnet.mongodb.controller;

import com.infnet.mongodb.model.Student;
import com.infnet.mongodb.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/*
    @RestController:
        * Combina @Controller + @ResponseBody → todos os métodos retornam JSON automaticamente.
    @RequestMapping("/students")
        * Define o caminho base da API.
*/
@RestController
@RequestMapping("/students")
public class StudentController {
    // Injeta automaticamente uma instância do StudentService, instancia a classe
    @Autowired
    private StudentService studentService;

    // endpoint GET - Busca todos os alunos
    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
        List<Student> students = studentService.getAll();
        return ResponseEntity.ok(students);
    }

    // endpoint POST - Cria um Aluno
    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student student) {
        Student created = this.studentService.create(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // endpoint PUT - Atualiza um aluno pelo id
    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable String id, @RequestBody Student student) {
        Student updated = this.studentService.update(id, student);
        return ResponseEntity.ok(updated);
    }

    // endpoint DELETE - Deleta um aluno pelo id
    @DeleteMapping("/{id}")
    public ResponseEntity<Student> delete(@PathVariable String id) {
        Student deleted = this.studentService.delete(id);
        return ResponseEntity.ok(deleted);
    }
}

