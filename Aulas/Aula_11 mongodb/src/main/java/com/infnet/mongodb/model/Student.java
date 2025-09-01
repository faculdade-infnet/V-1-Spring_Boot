package com.infnet.mongodb.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
  @Setter e @Getter
    * Gera automaticamente os métodos get e set para todos os atributos da classe.
  @Document
    * Indica que essa classe é um documento do MongoDB.
    * student é o nome da coleção e da classe
*/
@Setter@Getter@Document
public class Student {
    // Atributos
    // Id = identificador único
    @Id
    private String id;
    private String name;
    private String email;
}
