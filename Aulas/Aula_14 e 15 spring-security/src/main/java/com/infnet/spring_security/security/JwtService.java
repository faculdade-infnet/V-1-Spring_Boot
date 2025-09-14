package com.infnet.spring_security.security;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

//1- Recebe o usuário autenticado (Authentication).
//2- Pega roles/permissões e cria uma string "scope".
//3- Cria os claims do token (issuer, usuário, expiração, roles).
//4- Codifica os claims em um token JWT válido usando JwtEncoder.
//5- Retorna o token como string.
//6- Esse token depois pode ser enviado no header Authorization: Bearer <token> para acessar endpoints protegidos.
@Service
public class JwtService {
    private final JwtEncoder encoder;

    // JwtEncoder é usado para codificar os claims em um token JWT.
    public JwtService(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    // Metodo que recebe um Authentication (usuário autenticado) e retorna uma string JWT.
    public String generateToken(Authentication authentication) {
        // now → momento atual.
        // expiry → tempo de expiração do token em segundos (10 horas neste caso: 36000s).
        Instant now = Instant.now();
        long expiry = 36000L;

        // Pega todas as permissões (roles) do usuário: authentication.getAuthorities().
        //.map(GrantedAuthority::getAuthority) → transforma cada autoridade em uma string.
        //.collect(Collectors.joining(" ")) → junta todas as strings em uma só, separadas por espaço.
        //Resultado: algo como "read write admin" que será adicionado ao token.
        String scope = authentication
                .getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors
                        .joining(" "));

        // Cria os claims do JWT, ou seja, os dados contidos no token:
        //issuer("spring-security") → quem gerou o token.
        //issuedAt(now) → quando o token foi gerado.
        //expiresAt(now.plusSeconds(expiry)) → quando o token expira.
        //subject(authentication.getName()) → identifica o usuário (username).
        //claim("scope", scope) → adiciona as permissões do usuário.
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("spring-security")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();

        // JwtEncoderParameters.from(claims) → prepara os claims para serem codificados.
        //encoder.encode(...) → cria o JWT codificado.
        //.getTokenValue() → retorna o token como string que será enviado ao cliente.
        return encoder.encode(
                        JwtEncoderParameters.from(claims))
                .getTokenValue();
    }

}
