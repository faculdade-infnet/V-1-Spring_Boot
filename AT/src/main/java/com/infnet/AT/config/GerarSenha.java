package com.infnet.AT.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GerarSenha {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String senha = "123456"; // sua senha
        String hash = encoder.encode(senha);
        System.out.println("Senha criptografada: " + hash);
    }
}
