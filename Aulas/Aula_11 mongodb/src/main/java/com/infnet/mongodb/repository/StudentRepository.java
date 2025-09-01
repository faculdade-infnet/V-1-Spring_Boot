package com.infnet.mongodb.repository;


import com.infnet.mongodb.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

/*
    * A implemetnação concreta é criada em tempo de execução e MongoRepository contém internamente os métodos:
        * save(Student student)
        * findById(String id)
        * indAll()
        * deleteById(String id)
        * existsById(String id)
    *
*/
public interface StudentRepository extends MongoRepository<Student, String> {
}
