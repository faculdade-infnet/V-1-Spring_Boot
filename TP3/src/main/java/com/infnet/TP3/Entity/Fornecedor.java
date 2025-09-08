package com.infnet.TP3.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Table(name = "tb_fornecedor")
@Entity
public class Fornecedor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cnpj;
    private String telefone;
}
