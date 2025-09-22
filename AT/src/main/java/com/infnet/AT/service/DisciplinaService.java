package com.infnet.AT.service;

import com.infnet.AT.entity.Disciplina;
import com.infnet.AT.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaService {
    @Autowired
    private DisciplinaRepository repository;


    public List<Disciplina> getAll() {
        return repository.findAll();
    }

    public Disciplina create(Disciplina obj) {
        return repository.save(obj);
    }
}

