package com.infnet.cadastro.repository;

import com.infnet.cadastro.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

// Necessário implementar JpaRepository para ter as funções de um CRUD no banco de dados
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
