package com.infnet.TP3.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Table(name = "tb_funcionario")
@Entity
public class Funcionario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cargo;
    private BigDecimal salario;
}
