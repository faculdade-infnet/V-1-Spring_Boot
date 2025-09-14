package com.infnet.spring_security.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.infnet.spring_security.repository.UserRepository;

// Implementa UserDetailsService, então precisa obrigatoriamente sobrescrever o metodo loadUserByUsername.
// 1- Recebe um username quando alguém tenta logar.
// 2- Busca o usuário no banco via UserRepository.
// 3- Se achar, cria um UserAuthenticated (que o Spring Security consegue usar).
// 4- Se não achar, lança uma exceção.
// 5- O Spring Security então usa o UserDetails retornado para autenticar o usuário e verificar suas permissões.
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    // Isso é injeção de dependência: o Spring vai passar uma instância do UserRepository automaticamente quando criar UserDetailsServiceImpl.
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Metodo obrigatório da interface UserDetailsService.
    // Recebe um username e deve retornar um objeto UserDetails correspondente a esse usuário.
    // Se o usuário não existir, deve lançar UsernameNotFoundException.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> new UserAuthenticated(user))
                .orElseThrow(
                        () -> new UsernameNotFoundException("User Not Found with username: " + username));
    }

}
