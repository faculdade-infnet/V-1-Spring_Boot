package com.infnet.cadastro.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

// Gera um contrutor com todos os atributos como todos os construtores
@AllArgsConstructor
// Gera um contrutor vazio
@NoArgsConstructor
@Getter
@Setter
// Gera um método equal e um hascode
@EqualsAndHashCode(of = "id")

// Define que essa classe será uma entidade mapeada para o JPA
@Entity
// Nome da abela com os reespectivos atributos
@Table(name = "tb_aluno")
// Serializable - permite a persistencia no banco de dados
public class Aluno implements Serializable {
    // indica que esse atributo é a chave primária do banco de dados
    @Id
    // Valor do id será gerado automaticamente no banco  de forma única, sequêncial e incremental
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
}
