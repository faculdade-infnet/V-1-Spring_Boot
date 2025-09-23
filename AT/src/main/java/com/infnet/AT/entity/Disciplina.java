package com.infnet.AT.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "tb_disciplina")
@Entity
public class Disciplina implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String codigo;

    @ManyToMany
    @JoinTable(
            name = "aluno_disciplina",
            joinColumns = @JoinColumn(name = "disciplina_id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id")
    )
    @Builder.Default
    @JsonIgnore
    private Set<Aluno> alunos = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "notas", joinColumns = @JoinColumn(name = "disciplina_id"))
    @MapKeyJoinColumn(name = "aluno_id")
    @Column(name = "nota")
    @Builder.Default
    @JsonIgnore
    private Map<Aluno, Double> notas = new HashMap<>();

    // Métodos auxiliares
    public void adicionarAluno(Aluno aluno) {
        this.alunos.add(aluno);
        aluno.getDisciplinas().add(this);
    }
}

