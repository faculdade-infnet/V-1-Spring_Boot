package com.infnet.TP3.repository;

import com.infnet.TP3.Entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
