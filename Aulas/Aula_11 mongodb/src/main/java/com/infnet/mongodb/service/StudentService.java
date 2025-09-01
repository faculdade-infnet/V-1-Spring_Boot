package com.infnet.mongodb.service;

import com.infnet.mongodb.model.Student;
import com.infnet.mongodb.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/*
    Centraliza a lógica de negócio, evitando que os controllers lidem diretamente com o banco.
    Realiza as operações no Mongo DB
*/
@Service
public class StudentService {
    // injeta automaticamente uma instância do StudentRepository, instancia a classe
    @Autowired
    private StudentRepository studentRepository;

    // Busca todos os alunos
    public List<Student> getAll() {
        return this.studentRepository.findAll();
    }

    // Cria um Aluno
    public Student create(Student student) {
        return this.studentRepository.save(student);
    }

    // Atualiza um aluno pelo id
    public Student update(String id, Student student) {
        Optional<Student> existingStudent = this.studentRepository.findById(id);

        if (existingStudent.isEmpty()) {
            throw new RuntimeException("Aluno com ID " + id + " não encontrado");
        }

        Student updated = existingStudent.get();
        updated.setEmail(student.getEmail());
        updated.setName(student.getName());

        return this.studentRepository.save(updated);
    }

    // Deleta um aluno pelo id
    public Student delete(String id) {
        Optional<Student> existingStudent = this.studentRepository.findById(id);

        if (existingStudent.isEmpty()) {
            throw new RuntimeException("Aluno com ID " + id + " não encontrado");
        }

        this.studentRepository.deleteById(id);
        return existingStudent.get();
    }
}
