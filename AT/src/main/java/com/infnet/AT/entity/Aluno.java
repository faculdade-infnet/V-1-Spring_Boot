package com.infnet.AT.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Table(name = "tb_aluno")
@Entity
public class Aluno implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String endereco;

    // Relacionamento muitos-para-muitos com disciplina
    @ManyToMany(mappedBy = "alunos")
    @Builder.Default
    private Set<Disciplina> disciplinas = new HashSet<>();

    public void adicionarDisciplina(Disciplina disciplina) {
        this.disciplinas.add(disciplina);
        disciplina.getAlunos().add(this);
    }

    public void removerDisciplina(Disciplina disciplina) {
        this.disciplinas.remove(disciplina);
        disciplina.getAlunos().remove(this);
    }
}
