package com.infnet.spring_security.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infnet.spring_security.security.AuthenticationService;

// É responsável pelo login.
// O cliente manda usuário/senha e se as credenciais forem corretas, o Spring cria o Authentication.
// O controller devolve um token JWT e esse token será usado nas próximas requisições para acessar os endpoints privados (ex: /private).
// Em resumo: o AuthenticationController é a porta de entrada para obter o token JWT.
@RestController
public class AuthenticationController {
    // Br: Serviço que processa as autenticações do usuário
    // En: Service that processes user authentications
    @Autowired
    private AuthenticationService authenticationService;

    // Br: Injeta automaticamente o objeto Authentication, que contém o usuário e senha já validados.
    // En: Autocaticaly Injects the autenticated object, that contains user and paqssword tha has ben valideted
    // Br: Chama o AuthenticationService para gerar um JWT e retorna o token para o cliente.
    // En: Call AuthenticationService to generate a one JWT and returns the token to the cliente
    @PostMapping("authenticate")
    public String authenticate(Authentication authentication) {
        return authenticationService.authenticate(authentication);
    }
}
