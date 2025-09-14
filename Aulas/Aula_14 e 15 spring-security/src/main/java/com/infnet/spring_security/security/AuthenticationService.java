package com.infnet.spring_security.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

// Essa classe é o elo entre o login e o JWT.
// Quando o usuário se autentica com login/senha, o Spring cria o objeto Authentication.
// O AuthenticationService pega esse objeto e gera um token JWT para o cliente usar nas próximas requisições.
// Em resumo: ela transforma a autenticação em um token JWT.
@Service
public class AuthenticationService {
    private JwtService jwtService;

    public AuthenticationService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public String authenticate(Authentication authentication) {
        return jwtService.generateToken(authentication);
    }
}
