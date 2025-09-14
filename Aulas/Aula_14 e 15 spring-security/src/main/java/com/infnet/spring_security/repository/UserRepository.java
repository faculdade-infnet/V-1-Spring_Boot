package com.infnet.spring_security.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.infnet.spring_security.model.User;

// Br: Extends CrudRepository<User, String> → herda métodos prontos para CRUD (Create, Read, Update, Delete) para á entidade User.
// En: Extends CrudRepository<User, String> → inheritance completed CRUD methods (Create, Read, Update, Delete) for the User entity.

// Br: O segundo parâmetro (String) é do tipo da chave primária, que no seu caso é o username.
// Br: The second parameter (String) is the primary key type, which in your case is username.
public interface UserRepository extends CrudRepository<User, String> {
    // Br: Busca um usuario pelo username e retorna Optional<User> para tratar o caso de não encontrar o usuário sem lançar erro.
    // En: Searches for a user by username and returns Optional<User> to handle the case of not finding the user without throwing an error.
    // Br: Metodo é usado pelo Spring Security (UserDetailsServiceImpl) para carregar o usuário no login.
    // En: Method is used by Spring Security (UserDetailsServiceImpl) to load the user at login.
    Optional<User> findByUsername(String username);
}
