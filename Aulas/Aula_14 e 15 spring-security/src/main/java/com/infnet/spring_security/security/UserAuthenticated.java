package com.infnet.spring_security.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.infnet.spring_security.model.User;

// A classe faz a ponte entre o seu modelo de usuário (User) e o Spring Security, permitindo que o framework use seu usuário para autenticação e autorização.
// UserDetails é uma interface do Spring Security que define métodos que o framework precisa para autenticar e autorizar um usuário.
public class UserAuthenticated implements UserDetails {
    private final User user;

    public UserAuthenticated(User user) {
        this.user = user;
    }

    // Retorna o nome de usuário do usuário real (do objeto User).
    // É usado pelo Spring Security para identificação do usuário durante login
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // Retorna a senha do usuário.
    // O Spring Security precisa disso para verificar se a senha digitada corresponde à senha armazenada.
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    // Retorna uma lista de permissões (roles) do usuário no caso de leitura "read".
    // Ou seja, todos usuario teraoo, neste exemplo, apenas a permissao "read"
    //Nota: Você poderia retornar permissões dinâmicas com base no usuário real, mas aqui está fixo.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "read");
    }

    // isAccountNonExpired() → conta expirada? true significa não expirada.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //isAccountNonLocked() → conta bloqueada? true significa não bloqueada.
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //isCredentialsNonExpired() → senha expirada? true significa senha válida.
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //isEnabled() → usuário habilitado? true significa ativo.
    @Override
    public boolean isEnabled() {
        return true;
    }
}
