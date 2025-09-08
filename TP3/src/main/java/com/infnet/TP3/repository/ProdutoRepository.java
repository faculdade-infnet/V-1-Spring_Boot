package com.infnet.TP3.repository;

import com.infnet.TP3.Entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
