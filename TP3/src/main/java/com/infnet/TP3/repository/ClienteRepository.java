package com.infnet.TP3.repository;

import com.infnet.TP3.Entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
